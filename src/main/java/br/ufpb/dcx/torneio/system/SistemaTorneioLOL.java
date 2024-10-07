package br.ufpb.dcx.torneio.system;

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

    private Gravador gravador;

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
    public boolean cadastrarJogador(Jogador jogador) throws Exception {
        return false;
    }

    @Override
    public boolean removerJogador() throws Exception {
        return false;
    }

    @Override
    public List<Jogador> listaJogadores() {
        return List.of();
    }

    @Override
    public boolean criarNovaEquipe(String nomeEquipe) throws Exception {
        return false;
    }

    @Override
    public boolean adicionaAEquipe(Equipe equipe) throws Exception {
        return false;
    }

    @Override
    public boolean removerDaEquipe(Jogador jogador, Equipe equipe) throws Exception {
        return false;
    }

    @Override
    public Jogador pesquisarJogadorPorNick(String nickname) throws Exception {
        return null;
    }

    @Override
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws Exception {
        return null;
    }
}
