package br.ufpb.dcx.torneio.system;

import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;

import java.io.IOException;
import java.util.List;

public interface SistemaTorneio {
     public List<Jogador> iniciarSistema() throws IOException;
     public void finalizarSistema(List<Jogador> listaJogadores) throws IOException;
     public boolean cadastrarJogador(Jogador jogador) throws Exception; //especificar qual exceção

     public boolean removerJogador() throws Exception; //decidir o quais parametros e quais exceções
     public List<Jogador> listaJogadores(); //dispara alguma exceção?

    public boolean criarNovaEquipe(String nomeEquipe) throws Exception; //especificar exceção
    public boolean adicionaAEquipe(Equipe equipe) throws Exception; //especificar exceção
    public boolean removerDaEquipe(Jogador jogador, Equipe equipe) throws Exception; //especificar exceção
    public Jogador pesquisarJogadorPorNick(String nickname) throws  Exception; //especificar exceção
    public Jogador pesquisarJogadorPorNomeDaPessoa(String nomeDaPessoa) throws Exception; //especificar exceção
}