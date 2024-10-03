package br.ufpb.dcx.torneio.entitie;

import java.util.List;
import java.util.Objects;

public class Equipe {
    private String nomeDaEquipe;
    private List<Jogador> listaJogadores;

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores){
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores= listaJogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return Objects.equals(nomeDaEquipe, equipe.nomeDaEquipe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nomeDaEquipe);
    }

    public List<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(List<Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public String getNomeDaEquipe() {
        return nomeDaEquipe;
    }

    public void setNomeDaEquipe(String nomeDaEquipe) {
        this.nomeDaEquipe = nomeDaEquipe;
    }
}