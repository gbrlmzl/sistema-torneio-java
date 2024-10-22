package br.ufpb.dcx.torneio.Entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Equipe implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private String nomeDaEquipe;                     //Chave
    private List<Jogador> listaJogadores;
    private Integer pontuacao;

    public Equipe(String nomeDaEquipe) {
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores = new ArrayList<>();
        this.pontuacao = 0;
    }

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores) {
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores = listaJogadores;
        this.pontuacao = 0;
    }

    public Equipe(String nomeDaEquipe, List<Jogador> listaJogadores, Integer pontuacao) {
        this.nomeDaEquipe = nomeDaEquipe;
        this.listaJogadores = listaJogadores;
        this.pontuacao = pontuacao;
    }

    public Equipe() {
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

    public boolean estaCheia() {
        return listaJogadores.size() == 5;
    }


    public void adicionaJogador(Jogador jogador) {
        this.listaJogadores.add(jogador);
    }

    public void removeJogador(Jogador jogador) {
        this.listaJogadores.remove(jogador);
    }

    public boolean verificaJogador(Jogador jogador) {
        return listaJogadores.contains(jogador);
    }

    @Override
    public String toString() {
        StringBuilder mensagem = new StringBuilder(
                "Equipe: " + getNomeDaEquipe() + "\nJogadores:\n");
        for (Jogador jogador : getListaJogadores()) {
            mensagem.append("- ").append(jogador.getNickName()).append("\n");
        }
        return mensagem.toString();
    }
}