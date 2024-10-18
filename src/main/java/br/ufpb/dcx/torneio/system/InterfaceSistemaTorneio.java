package br.ufpb.dcx.torneio.system;

import br.ufpb.dcx.torneio.entitie.ELOS;
import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;

import java.io.IOException;
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
     public void removerJogador(String tag) throws Exception; //decidir o quais parametros e quais exceções

    /**
     * @return retorna uma lista contendo todos os Jogadores
     */
     public List<Jogador> listaJogadores(); //dispara alguma exceção?

    /**
     *
     * @param nomeEquipe nome da Equipe
     * Cadastra a Equipe no map Equipes
     * @throws Exception caso a Equipe já exista
     */
    public void criarNovaEquipe(String nomeEquipe) throws Exception; //especificar exceção

    /**
     *
     * @param equipe Equipe que vai receber o Jogador
     * @param jogador que vai entrar para a equipe
     * @throws Exception caso a Equipe não exista // Caso o Jogador Não exista // Caso a Equipe esteja cheia //  Caso o jogador já tenha Equipe
     */
    public void adicionaAEquipe(Equipe equipe, Jogador jogador) throws Exception;

    /**
     *
     * @param tag do jogador que vai ser removido
     * @param equipe que vai perder o jogador
     * @throws Exception caso jogador não exista para essa tag(pesquisarPorTag) // caso a equipe não exista // caso o jogador não esteja na equipe
     */
    public void removerDaEquipe(String tag, Equipe equipe) throws Exception; //especificar exceção

    /**
     *
     * @param tag do jogador
     * @return Jogador que tem a tag
     * @throws Exception caso o jogador não exista
     */
    public Jogador pesquisarJogadorPorTag(String tag) throws Exception;

    /**
     *
     * @param nickname nick do jogador
     * @return lista de jogadores que têm esse nick com suas tags
     * @throws Exception caso o nickname não exista
     */
    public List<Jogador> pesquisarJogadorPorNick(String nickname) throws  Exception; //especificar exceção

    /**
     *
     * @param nomeDaEquipe nome da Equipe
     * @return a Equipe encontrada
     * @throws Exception caso a Equipe Não exista
     */
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws Exception; //especificar exceção
}