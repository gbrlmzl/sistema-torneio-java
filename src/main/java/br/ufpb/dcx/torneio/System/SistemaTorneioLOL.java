package br.ufpb.dcx.torneio.System;

import br.ufpb.dcx.torneio.Exception.*;
import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Gravador.Gravador;

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
        this.equipes = new TreeMap<>();
        this.jogadores = new TreeMap<>();
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
    public void cadastrarJogador(Jogador jogador) throws JogadorJaExisteException, ObjetoNuloException {
        if (jogador == null)
            throw new ObjetoNuloException("Jogador nulo");
        else if (jogadores.containsValue(jogador))
            throw new JogadorJaExisteException("Jogador já existe");
        jogadores.put(jogador.getTag(), jogador);       //Lembrar de no main fazer a separação dos construtores
        JOptionPane.showMessageDialog(null, "Jogador" + ANSI_RED + jogador.getTag() + jogador.getNickName() + ANSI_RESET + " cadastrado com sucesso!");
    }

    @Override
    public void removerJogador(String tag) throws ObjetoNuloException {
        Jogador jogador =  jogadores.get(tag);
        if (jogador == null)
            throw new ObjetoNuloException("Jogador nulo");
        jogadores.remove(tag);
        JOptionPane.showMessageDialog(null, "Jogador " + ANSI_RED + jogador.getTag() + jogador.getNickName() + ANSI_RESET + " removido!");
    }

    @Override
    public Collection<Jogador> listaJogadores() throws JogadorNaoEncontradoException {
        if (jogadores.isEmpty()) {
            throw new JogadorNaoEncontradoException("Jogador não encontrado");
        }

        return jogadores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Jogador::getNickName)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }


    @Override
    public void criarNovaEquipe(String nomeEquipe) throws EquipeJaExisteException {
        if (!equipes.containsKey(nomeEquipe.toUpperCase()))
            throw new EquipeJaExisteException("Uma equipe já existe com esse nome");   //Equipe com esse nome já Existe
        equipes.put(nomeEquipe, new Equipe(nomeEquipe.toUpperCase()));
        JOptionPane.showMessageDialog(null, "Equipe criada " + ANSI_BLUE + nomeEquipe + ANSI_RESET + "com sucesso!");
    }

    @Override
    public void adicionaJogadorAEquipe(String nomeDaEquipe, String tagJogador) throws JogadorJaTemEquipeException, EquipeCheiaException, JogadorNaoEncontradoException, EquipeNaoEncontradaException {
        Jogador jogador = pesquisarJogadorPorTag(tagJogador);
        Equipe equipe = pesquisarEquipePeloNome(nomeDaEquipe);
        if (jogador.temEquipe() )
            throw new JogadorJaTemEquipeException(jogador.getNickName() + " já está em uma equipe");
        if (equipe.estaCheia())
            throw new EquipeCheiaException(equipe + " esta cheia");
        equipe.adicionaJogador(jogador);
        JOptionPane.showMessageDialog(null,"Jogador adicionado a equipe com sucesso");
    }

    @Override
    public void removerDaEquipe(String tagJogador, String nomeDaEquipe) throws JogadorNaoTemEquipeException, JogadorNaoEstaNaEquipeException, JogadorNaoEncontradoException, EquipeNaoEncontradaException {
        Jogador jogador = pesquisarJogadorPorTag(tagJogador);
        Equipe equipe = pesquisarEquipePeloNome(nomeDaEquipe);
        if (!jogador.temEquipe())
            throw new JogadorNaoTemEquipeException(jogador.getNickName() + " não tem equipe");
        if (!equipe.verificaJogador(jogador))
            throw new JogadorNaoEstaNaEquipeException("Jogador não está nesta equipe");
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
    public Jogador pesquisarJogadorPorTag(String tag) throws JogadorNaoEncontradoException {
        return jogadores.entrySet().stream()
                .filter(entry -> entry.getKey().equals(tag))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não existe"));
    }

    @Override
    public Equipe pesquisarEquipePeloNome(String nomeDaEquipe) throws EquipeNaoEncontradaException {
        return equipes.entrySet().stream()
                .filter(entry -> entry.getKey().equals(nomeDaEquipe))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new EquipeNaoEncontradaException("Equipe não existe"));
    }

}
