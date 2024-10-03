package br.ufpb.dcx.torneio.entitie;

import java.util.Objects;

public class Jogador {
    private String nickName;
    private String elo; //verificar se fica melhor ser um int, ao inves de ser o nome do elo
                        //ou a pontuação do elo dele logo de cara

    private String nomeEquipe; //verificar se isso é necessário ou não

    public Jogador(String nickName, String elo){
        this.nickName = nickName;
        this.elo = elo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(nickName, jogador.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }

    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }
}