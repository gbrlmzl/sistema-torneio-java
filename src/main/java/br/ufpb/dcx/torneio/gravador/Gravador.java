package br.ufpb.dcx.torneio.gravador;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Gravador {

    public void save(Map<String, Object> map, String fileName) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oss.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> recarrega(String fileName) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<Integer, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}