package br.ufpb.dcx.torneio.entitie;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Equipe implements Serializable {


    private String nomeDaEquipe;                     //Chave
    private List<Jogador> listaJogadores;
    private Integer pontuacao;

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores){
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores= listaJogadores;
        this.pontuacao = 0;
    }//Construtor de Equipe COM geração de tag

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores, Integer pontuacao) {
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores = listaJogadores;
        this.pontuacao = pontuacao;
    }//Construtor de Equipes SEM geração de tag

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

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}