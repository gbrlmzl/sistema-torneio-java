package br.ufpb.dcx.torneio.system;

import br.ufpb.dcx.torneio.entitie.ELOS;
import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;
import br.ufpb.dcx.torneio.gravador.Gravador;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaTorneioLOL implements InterfaceSistemaTorneio{

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private Map<String, Equipe> equipes;
    private Map<String, Jogador> jogadores;

    private static final String fileNameEquipes = "equipes.dat";
    private static final String fileNameJogadores = "jogadores.dat";

    private final Gravador gravador;

    public SistemaTorneioLOL(){
        this.equipes = new HashMap<>();
        this.jogadores = new HashMap<>();
        this.gravador = new Gravador();
    }

    @Override
    public void iniciarSistema() throws IOException {
        equipes = gravador.recarregaEquipes(fileNameEquipes, this);
        jogadores = gravador.recarregaJogador(fileNameJogadores, this);
    }

    @Override
    public void finalizarSistema() throws IOException {
        gravador.saveEquipe(equipes, fileNameEquipes,this);
        gravador.saveJogador(jogadores, fileNameJogadores,this);
    }

    @Override
    public void cadastrarJogador(Jogador jogador) throws Exception {
        if (jogador == null)
            throw new NullPointerException();
        else if (jogadores.containsValue(jogador))
            throw new Exception("Jogador Ja cadastrado");
        jogadores.put(jogador.getTag(), jogador);       //Lembrar de no main fazer a separação dos construtores
        JOptionPane.showMessageDialog(null, "Jogador" + ANSI_RED + jogador.getTag() + jogador.getNickName() + ANSI_RESET + " cadastrado com sucesso!");
    }

    @Override
    public void removerJogador(String tag) throws Exception {
        Jogador jogador =  jogadores.get(tag);
        if (jogador == null)
            throw new NullPointerException();
        jogadores.remove(tag);
        JOptionPane.showMessageDialog(null, "Jogador " + ANSI_RED + jogador.getTag() + jogador.getNickName() + ANSI_RESET + " removido!");
    }

    @Override
    public Collection<Jogador> listaJogadores() throws Exception {
        if (jogadores.isEmpty()) {
            throw new NullPointerException("Nenhum jogador cadastrado.");
        }

        return jogadores.values().stream().collect(Collectors.toList());
    }


    @Override
    public void criarNovaEquipe(String nomeEquipe) throws Exception {
        if (!equipes.containsKey(nomeEquipe))
            throw new NullPointerException("Equipe com este nome já existe");   //Equipe com esse nome já Existe
        equipes.put(nomeEquipe, new Equipe(nomeEquipe));
        JOptionPane.showMessageDialog(null, "Equipe criada " + ANSI_BLUE + nomeEquipe + ANSI_RESET + "com sucesso!");
    }

    @Override
    public void adicionaJogadorAEquipe(String nomeDaEquipe, String tagJogador) throws Exception {
        Jogador jogador = pesquisarJogadorPorTag(tagJogador);
        Equipe equipe = pesquisarEquipePeloNome(nomeDaEquipe);
        if (jogador.temEquipe() )
            throw new Exception("Jogador já tem equipe");
        if (equipe.estaCheia())
            throw new Exception("Equipe já está cheia");
        equipe.adicionaJogador(jogador);
        JOptionPane.showMessageDialog(null,"Jogador adicionado a equipe com sucesso");
    }

    @Override
    public void removerDaEquipe(String tagJogador, String nomeDaEquipe) throws Exception {
        Jogador jogador = pesquisarJogadorPorTag(tagJogador);
        Equipe equipe = pesquisarEquipePeloNome(nomeDaEquipe);
        if (!jogador.temEquipe())
            throw new Exception("Este jogador não está em uma equipe");
        if (!equipe.verificaJogador(jogador))
            throw new Exception("Equipe não tem esse jogador");
        equipe.removeJogador(jogador);
        JOptionPane.showMessageDialog(null,"Jogador removido com sucesso");
    }

    @Override
    public Collection<Equipe> listaEquipes() throws Exception {
        if (equipes.isEmpty())
            throw new NullPointerException("Nenhum equipe cadastrado.");
        return equipes.values().stream().collect(Collectors.toList());
    }
    @Override
    public Jogador pesquisarJogadorPorTag(String tag) throws Exception {
        return jogadores.entrySet().stream()
                .filter(entry -> entry.getKey().equals(tag))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new Exception("Jogador não existe"));
    }

    @Override
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws Exception {
        return equipes.entrySet().stream()
                .filter(entry -> entry.getKey().equals(nomeDaEquipe))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new Exception("Equipe não existe"));
    }

}
