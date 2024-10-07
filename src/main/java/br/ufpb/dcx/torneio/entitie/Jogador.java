package br.ufpb.dcx.torneio.entitie;

import java.io.Serializable;
import java.util.Objects;

class TagJogador{
    private static int contador = 0;
    private final int id;

    public TagJogador(){
        this.id = ++contador;
    }

    @Override
    public String toString(){
        return "#" + id;
    }
}


public class Jogador implements Serializable {

    private String nickName;

    private ELOS elo;

    private TagJogador tagJogador;

    private Equipe equipe;

    public Jogador(String nickName, ELOS elo, Equipe equipe){
        this.nickName = nickName;
        this.elo = elo;
        this.equipe = equipe;
        this.tagJogador = new TagJogador();
    }//Construtor de Jogador com equipe COM geração de tag

    public Jogador(String nickName, ELOS elo, Equipe equipe, TagJogador tagJogador){
        this.nickName = nickName;
        this.elo = elo;
        this.equipe = equipe;
        this.tagJogador = tagJogador;
    }//Construtor de jogador com equipe SEM geração de tag

    public Jogador(String nickName, ELOS elo, TagJogador tagJogador){
        this.nickName = nickName;
        this.elo = elo;
        this.tagJogador = tagJogador;
    }//Construtor de Jogador sem equipe SEM geração de tag

    public Jogador(String nickName, ELOS elo){
        this.nickName = nickName;
        this.elo = elo;
        this.tagJogador = new TagJogador();
    }//Construtor de Jogador sem equipe COM geração de tag

    public Jogador() {
        this("",null,null,null);
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

    public ELOS getElo() {
        return elo;
    }

    public void setElo(ELOS elo) {
        this.elo = elo;
    }

    public TagJogador getTag() {
        return tagJogador;
    }

    public void setTagJogador(TagJogador tag) {
        this.tagJogador = tag;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}