package br.ufpb.dcx.torneio.entitie;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

class TagEquipe{
    private static int contador = 0;
    private final int id;

    public TagEquipe(){
        this.id = ++contador;
    }

    @Override
    public String toString(){
        return "#" + id;
    }
}

public class Equipe implements Serializable {


    private String nomeDaEquipe;
    private List<Jogador> listaJogadores;
    private Integer pontuacao;
    private TagEquipe tagEquipe;

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores){
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores= listaJogadores;
        this.tagEquipe = new TagEquipe();
        this.pontuacao = 0;
    }//Construtor de Equipe COM geração de tag

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores, TagEquipe tagEquipe, Integer pontuacao) {
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores = listaJogadores;
        this.tagEquipe = tagEquipe;
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

    public TagEquipe getTagEquipe() {
        return tagEquipe;
    }

    public void setTagEquipe(TagEquipe tagEquipe) {
        this.tagEquipe = tagEquipe;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}