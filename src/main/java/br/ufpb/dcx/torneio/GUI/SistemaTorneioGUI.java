package br.ufpb.dcx.torneio.GUI;

import br.ufpb.dcx.torneio.Controller.*;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.*;

public class SistemaTorneioGUI extends JFrame{

    SistemaTorneioLOL sistema= new SistemaTorneioLOL();
    JMenuBar barraMenu = new JMenuBar();

    public SistemaTorneioGUI(){
        setTitle("Torneio");

        setSize(1100, 600);
        setLocation(150, 150);
        setResizable(false);
        setBackground(Color.lightGray);

        add(new JLabel());

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem cadastrarJogador = new JMenuItem("Cadastrar Jogador");
        JMenuItem cadastrarEquipe = new JMenuItem("Cadastrar Equipe");
        menuCadastro.add(cadastrarJogador);
        menuCadastro.add(cadastrarEquipe);


        JMenu menuRemocao = new JMenu("Remover");
        JMenuItem removerJogador = new JMenuItem("Remover Jogador");
        JMenuItem removerEquipe = new JMenuItem("Remover Equipe");
        menuRemocao.add(removerJogador);
        menuRemocao.add(removerEquipe);


        JMenu menuJogador = new JMenu("Jogador");
        JMenuItem listaJogadores = new JMenuItem("Listar todos os jogadores");
        JMenuItem pesquisarJogador = new JMenuItem("Pesquisar Jogador");
        menuJogador.add(listaJogadores);
        menuJogador.add(pesquisarJogador);


        JMenu menuEquipe = new JMenu("Gerenciamento de equipe");
        JMenuItem adicionarAEquipe = new JMenuItem("Adicionar novo jogador na equipe");
        JMenuItem removerDaEquipe = new JMenuItem("Remover jogador da equipe");
        JMenuItem pesquisarEquipe = new JMenuItem("Pesquisar Equipe");
        JMenuItem listaEquipes = new JMenuItem("Listar todas as equipes");
        menuEquipe.add(pesquisarEquipe);
        menuEquipe.add(adicionarAEquipe);
        menuEquipe.add(removerDaEquipe);
        menuEquipe.add(listaEquipes);

        //adicionando as ações
        cadastrarJogador.addActionListener(new SistemaTorneioCadastrarJogadorController(sistema, this));
        cadastrarEquipe.addActionListener(new SistemaTorneioCriarNovaEquipeController(sistema, this));

        removerJogador.addActionListener(new SistemaTorneioRemoverJogadorController(sistema, this));
        removerEquipe.addActionListener(new SistemaTorneioRemoverEquipeController(sistema, this));

        listaJogadores.addActionListener(new SistemaTorneioListaJogadoresController(sistema, this));
        pesquisarJogador.addActionListener(new SistemaTorneioPesquisarJogadorPorNickController(sistema, this));

        adicionarAEquipe.addActionListener(new SistemaTorneioAdicionarAEquipeController(sistema, this));
        removerDaEquipe.addActionListener(new SistemaTorneioRemoverDaEquipeController(sistema, this));
        pesquisarEquipe.addActionListener(new SistemaTorneioPesquisarEquipePeloNomeController(sistema, this));
        listaEquipes.addActionListener(new SistemaTorneioListaEquipesController(sistema, this));

        //adicionando opções na barra de menu
        barraMenu.add(menuCadastro);
        barraMenu.add(menuRemocao);
        barraMenu.add(menuJogador);
        barraMenu.add(menuEquipe);
        setJMenuBar(barraMenu);


    }

    public static void main(String[]args){
        SistemaTorneioGUI sistema = new SistemaTorneioGUI();
        sistema.setVisible(true);
        sistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}
