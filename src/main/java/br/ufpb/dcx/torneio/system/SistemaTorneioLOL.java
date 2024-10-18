package br.ufpb.dcx.torneio.system;

import br.ufpb.dcx.torneio.entitie.ELOS;
import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;
import br.ufpb.dcx.torneio.gravador.Gravador;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaTorneioLOL implements InterfaceSistemaTorneio{

    private Map<String, Object> equipes;
    private Map<String, Object> jogadores;

    private static final String fileNameEquipes = "equipes.dat";
    private static final String fileNameJogadores = "jogadores.dat";

    private final Gravador gravador;

    public SistemaTorneioLOL(){
        this.equipes = new HashMap<String, Object>();
        this.jogadores = new HashMap<String, Object>();
        this.gravador = new Gravador();
    }

    @Override
    public void iniciarSistema() throws IOException {
        equipes = gravador.recarrega(fileNameEquipes);
        jogadores = gravador.recarrega(fileNameJogadores);
    }

    @Override
    public void finalizarSistema() throws IOException {
        gravador.save(equipes, fileNameEquipes);
        gravador.save(jogadores, fileNameJogadores);
    }

    @Override
    public void cadastrarJogador(Jogador jogador) throws Exception {
        if (jogador == null)
            throw new NullPointerException();
        else if (jogadores.containsValue(jogador))
            throw new Exception("Jogador Ja cadastrado");
        jogadores.put(jogador.getTag(), jogador); //Lembrar de no main fazer a separação dos construtores
    }

    @Override
    public void removerJogador(String tag) throws Exception {
    }

    @Override
    public List<Jogador> listaJogadores() {
        return List.of();
    }

    @Override
    public void criarNovaEquipe(String nomeEquipe) throws Exception {
    }

    @Override
    public void adicionaAEquipe(Equipe equipe, Jogador jogador) throws Exception {
    }

    @Override
    public void removerDaEquipe(String tag, Equipe equipe) throws Exception {

    }

    @Override
    public Jogador pesquisarJogadorPorTag(String tag) throws Exception {
        return null;
    }

    @Override
    public List<Jogador> pesquisarJogadorPorNick(String nickname) throws Exception {
        return null;
    }

    @Override
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws Exception {
        return null;
    }
}
