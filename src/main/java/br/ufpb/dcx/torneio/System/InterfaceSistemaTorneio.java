package br.ufpb.dcx.torneio.System;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface InterfaceSistemaTorneio {
    /**
     * Inicializa o sistema atribuindo os jogadores/equipes aos maps do Sistema
      * @throws IOException caso dê erro no recarregamento
     */
    public void iniciarSistema() throws IOException;

    /**
     * Salva todos os objetos das duas listas em um arquivo .csv
     * @throws IOException caso ocorra erro durante o save
     */
     public void finalizarSistema() throws IOException;

    /**
     *
     * @param jogador é cadastrado
     * @throws Exception caso o jogador seja nulo ou caso o jogador já exista(Fazer verificação de necessidade)
     */
     public void cadastrarJogador(Jogador jogador) throws Exception; //especificar qual exceção

    /**
     *
     * @param tag recebe uma Tag de um Jogador para remove-lo
     * @throws Exception caso não encontre o jogador com essa tag
     */
     public void removerJogador(String tag) throws Exception;

    /**
     * @return retorna uma lista contendo todos os Jogadores
     */
     public Collection<Jogador> listaJogadores() throws Exception; //dispara alguma exceção?

    /**
     *
     * @param nomeEquipe nome da Equipe
     * Cadastra a Equipe no map Equipes
     * @throws Exception caso a Equipe já exista
     */
    public void criarNovaEquipe(String nomeEquipe) throws Exception; //especificar exceção

    /**
     *
     * @param nomeDaEquipe Equipe que vai receber o Jogador
     * @param tag do jogador que será pesquisado
     * @throws Exception caso a Equipe não exista // Caso o Jogador Não exista // Caso a Equipe esteja cheia //  Caso o jogador já tenha Equipe
     */
    public void adicionaJogadorAEquipe(String nomeDaEquipe, String tag) throws Exception;

    /**
     *
     * @param tagJogador do jogador que vai ser removido
     * @param nomeDaEquipe que vai perder o jogador
     * @throws Exception caso jogador não exista para essa tag(pesquisarPorTag) // caso a equipe não exista // caso o jogador não esteja na equipe
     */
    public void removerDaEquipe(String tagJogador, String nomeDaEquipe) throws Exception; //especificar exceção

    /**
     *
     * @throws Exception caso esteja vazia
     */
    public Collection<Equipe> listaEquipes() throws Exception;
    /**
     *
     * @param tag do jogador
     * @return Jogador que tem a tag
     * @throws Exception caso o jogador não exista
     */
    public Jogador pesquisarJogadorPorTag(String tag) throws Exception;

    /**
     *
     * @param nomeDaEquipe nome da Equipe
     * @return a Equipe encontrada
     * @throws Exception caso a Equipe Não exista
     */
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws Exception; //especificar exceção

    /**
     *
     * @param nomeDaEquipe nome da Equipe
     * @throws Exception caso a Equipe Não exista
     */
    public void removerEquipe(String nomeDaEquipe) throws Exception;

    /**
     *
     * @return lista de jogadores que não possuem equipe
     */
    public List<Jogador> pesquisaJogadoresSemEquipe();

    /**
     *
     * @return lista dos nomes dos jogadores que não possuem equipe
     */
    public List<String> pesquisaNomesJogadoresSemEquipe();

    /**
     * @param nickName nickName do jogador
     * @return Jogador com nickName
     * @throws Exception caso o jogador não exista
     */
    public Jogador pesquisaJogadorPorNickName(String nickName) throws Exception;
}