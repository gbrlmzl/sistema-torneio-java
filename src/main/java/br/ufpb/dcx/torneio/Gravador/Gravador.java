package br.ufpb.dcx.torneio.Gravador;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Entities.TagJogador;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import java.io.*;
import java.util.Map;

public class Gravador {

    public void saveJogador(Map<String, Jogador> map, String fileName, SistemaTorneioLOL sistema) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
            oss.writeObject(TagJogador.getContador());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Jogador> recarregaJogador(String fileName, SistemaTorneioLOL sistema) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Map<String, Jogador> map = (Map<String, Jogador>) ois.readObject();
            TagJogador.setContador((Integer) ois.readInt());
            return map;
        } catch (IOException | ClassNotFoundException e) {
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
    public Map<String, Equipe> recarregaEquipes(String fileName, SistemaTorneioLOL sistema) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Equipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}