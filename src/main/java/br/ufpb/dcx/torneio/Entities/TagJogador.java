package br.ufpb.dcx.torneio.Entities;

import java.io.Serial;
import java.io.Serializable;

public class TagJogador implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int contador = 0;
    private final int id;

    public TagJogador(){
        this.id = ++contador;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        TagJogador.contador = contador;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "#" + id;
    }
}