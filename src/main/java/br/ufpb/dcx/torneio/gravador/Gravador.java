package br.ufpb.dcx.torneio.gravador;

import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;
import br.ufpb.dcx.torneio.system.SistemaTorneioLOL;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Gravador {

    public void saveJogador(Map<String, Jogador> map, String fileName, SistemaTorneioLOL sistema) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void saveEquipe(Map<String, Equipe> map, String fileName, SistemaTorneioLOL sistema) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Jogador> recarregaJogador(String fileName, SistemaTorneioLOL sistema) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Jogador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Equipe> recarregaEquipes(String fileName, SistemaTorneioLOL sistema) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Equipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}