package br.ufpb.dcx.torneio.Gravador;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Entities.TagJogador;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Gravador {

    public void saveJogador(Map<String, Jogador> map, String fileName) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar jogadores: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Jogador> recarregaJogador(String fileName, SistemaTorneioLOL sistema) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Map<String, Jogador> map = (Map<String, Jogador>) ois.readObject();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            return new TreeMap<String, Jogador>();
        }
    }

    public void saveEquipe(Map<String, Equipe> map, String fileName) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar equipes: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Equipe> recarregaEquipes(String fileName, SistemaTorneioLOL sistema) {
        Map<String, Equipe> mapa = new TreeMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Equipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return mapa;
        }
    }

    public void saveContador(String fileName) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(TagJogador.getContador());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar contador: " + e.getMessage(), e);
        }
    }

    public void recarregaContador(String fileName) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            int contador = dis.readInt();
            TagJogador.setContador(contador);
        } catch (IOException e) {
            TagJogador.setContador(0);
        }
    }
}