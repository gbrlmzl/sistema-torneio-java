package br.ufpb.dcx.torneio.main;

import br.ufpb.dcx.torneio.Exception.SistemaTorneioLOLException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        SistemaTorneioLOL sistema = new SistemaTorneioLOL();
        try {
            sistema.iniciarSistema();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}