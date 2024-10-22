package br.ufpb.dcx.torneio.System;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.*;
import br.ufpb.dcx.torneio.Gravador.Gravador;


import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaTorneioLOL implements InterfaceSistemaTorneio{

    private JFrame janelaPrincipal;
    private Map<String, Equipe> equipes;
    private Map<String, Jogador> jogadores;

    private static final String fileNameEquipes = "equipes.dat";
    private static final String fileNameJogadores = "jogadores.dat";
    private static final String fileNameContador = "contador.dat";

    private final Gravador gravador;

    public SistemaTorneioLOL(){
        this.equipes = new TreeMap<>();
        this.jogadores = new TreeMap<>();
        this.gravador = new Gravador();
    }

    @Override
    public void iniciarSistema() throws IOException {
        equipes = gravador.recarregaEquipes(fileNameEquipes,this);
        jogadores = gravador.recarregaJogador(fileNameJogadores,this);
        gravador.recarregaContador(fileNameContador);
    }

    @Override
    public void finalizarSistema() throws IOException {
        gravador.saveEquipe(equipes, fileNameEquipes);
        gravador.saveJogador(jogadores, fileNameJogadores);
        gravador.saveContador(fileNameContador);
    }

    @Override
    public void cadastrarJogador(Jogador jogador) throws JogadorJaExisteException, ObjetoNuloException {
        if (jogador == null)
            throw new ObjetoNuloException("Jogador nulo");
        else if (jogadores.containsValue(jogador))
            throw new JogadorJaExisteException("Jogador já existe");
        jogadores.put(jogador.getTag(), jogador);       //Lembrar de no main fazer a separação dos construtores
    }

    @Override
    public void removerJogador(String tag) throws ObjetoNuloException {
        Jogador jogador =  jogadores.get(tag);
        if (jogador == null)
            throw new ObjetoNuloException("Jogador nulo");
        if(jogador.getEquipe() != null){
            jogador.getEquipe().removeJogador(jogador);
        }
        jogadores.remove(tag);
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
        if (equipes.containsKey(nomeEquipe))
            throw new EquipeJaExisteException("Uma equipe já existe com esse nome");   //Equipe com esse nome já Existe
        equipes.put(nomeEquipe, new Equipe(nomeEquipe));
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
        jogador.setEquipe(equipe);
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

        jogador.setEquipe(null);
    }

    @Override
    public Collection<Equipe> listaEquipes() throws NenhumaEquipeCadastradaException {
        if (equipes.isEmpty())
            throw new NenhumaEquipeCadastradaException("Nenhum equipe cadastrado.");
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

    @Override
    public void removerEquipe(String nomeDaEquipe) throws EquipeNaoEncontradaException {
        Equipe equipeRemover = pesquisarEquipePeloNome(nomeDaEquipe);
        for (Jogador j: equipeRemover.getListaJogadores())
            j.setEquipe(null);
        equipes.remove(nomeDaEquipe);
    }

    @Override
    public List<Jogador> pesquisaJogadoresSemEquipe(){
        List<Jogador> listaJogadoresSemEquipe = new ArrayList<>();
        for(Jogador j : jogadores.values()){
            if(j.getEquipe() == null){
                listaJogadoresSemEquipe.add(j);
            }
        }
        return listaJogadoresSemEquipe;
    }

    @Override
    public List<String> pesquisaNomesJogadoresSemEquipe(){
        List<String> listaJogadoresSemEquipe = new ArrayList<>();
        for(Jogador j : jogadores.values()){
            if(j.getEquipe() == null){
                listaJogadoresSemEquipe.add(j.getNickName());
            }
        }
        return listaJogadoresSemEquipe;
    }

    @Override
    public Jogador pesquisaJogadorPorNickName(String nickName) throws JogadorNaoEncontradoException {
        for(Jogador j : jogadores.values()){
            if(j.getNickName().equals(nickName)){
                return j;
            }
        }
        throw new JogadorNaoEncontradoException("Jogador não existe");
    }

    @Override
    public String consultarTagJogador(String nickName) throws JogadorNaoEncontradoException{
        try {
            Jogador jogador = pesquisaJogadorPorNickName(nickName);
            return jogador.getTag();
        } catch (JogadorNaoEncontradoException e) {
            throw new JogadorNaoEncontradoException("Jogador não existe");
        }
    }
}
