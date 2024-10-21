package main.java.br.ufpb.dcx.torneio.main;

import main.java.br.ufpb.dcx.torneio.Exception.SistemaTorneioLOLException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        SistemaTorneioLOL sistema = new SistemaTorneioLOL();
        try {
            sistema.iniciarSistema();
            System.out.println(sistema.listaJogadores());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SistemaTorneioLOLException e) {
            e.printStackTrace();
        }
    }
}