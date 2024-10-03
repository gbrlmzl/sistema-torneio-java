package br.ufpb.dcx.torneio.system;

import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SistemaGerenciaTorneio implements SistemaTorneio{
    //private Map<String,Equipe> mapEquipes;    repensar de ter apenas equipes aqui;


    @Override
    public List<Jogador> iniciarSistema() throws IOException {
        return List.of();
    }

    @Override
    public void finalizarSistema(List<Jogador> listaJogadores) throws IOException {

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
    public Jogador pesquisarJogadorPorNomeDaPessoa(String nomeDaPessoa) throws Exception {
        return null;
    }
}
