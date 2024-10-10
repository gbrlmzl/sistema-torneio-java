package br.ufpb.dcx.torneio.GUI;

import br.ufpb.dcx.torneio.Controller.*;
import br.ufpb.dcx.torneio.system.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.*;

public class SistemaTorneioGUI extends JFrame{
    ImageIcon menuPrincipalImg = new ImageIcon("imgs/menuPrincipal.png");
    SistemaTorneioLOL sistema = new SistemaTorneioLOL();
    JMenuBar barraMenu = new JMenuBar();

    public SistemaTorneioGUI(){
        setTitle("Torneio");

        setSize(1100, 499);
        setLocation(150, 150);
        setResizable(false);
        setBackground(Color.lightGray);


        add(new JLabel());
        JLabel planoDeFundoMenuPrincipal = new JLabel(menuPrincipalImg, JLabel.CENTER);
        getContentPane().add(planoDeFundoMenuPrincipal);
        JMenu menuJogador = new JMenu("Jogador");

        JMenuItem cadastrarJogador = new JMenuItem("Cadastrar jogador(a)");
        JMenuItem removerJogador = new JMenuItem("Remover jogador(a)");
        menuJogador.add(cadastrarJogador);
        menuJogador.add(removerJogador);
        //TODO


        JMenu menuEquipe = new JMenu("Equipe");

        JMenuItem criarEquipe = new JMenuItem("Criar equipe");
        JMenuItem removerMembro = new JMenuItem("Remover membro da equipe");
        JMenuItem adicionarMembro = new JMenuItem("Adicionar membro a equipe");
        menuEquipe.add(criarEquipe);
        menuEquipe.add(removerMembro);
        menuEquipe.add(adicionarMembro);


        JMenu menuPesquisa = new JMenu("Pesquisa");

        JMenuItem pesquisarEquipe = new JMenuItem("Pesquisar equipe");
        JMenuItem pesquisarTodosJogadores = new JMenuItem("Pesquisar todos jogadores");
        JMenuItem pesquisarJogador = new JMenuItem("Pesquisar jogador(a)");
        menuPesquisa.add(pesquisarJogador);
        menuPesquisa.add(pesquisarEquipe);
        menuPesquisa.add(pesquisarTodosJogadores);



        //adicionando as ações do menu Jogador
        cadastrarJogador.addActionListener(new SistemaTorneioCadastrarJogadorController(sistema, this));
        removerJogador.addActionListener(new SistemaTorneioRemoverJogadorController(sistema, this));


        //adicionando as ações do menu Equipe
        criarEquipe.addActionListener(new SistemaTorneioCriarNovaEquipeController(sistema, this));
        removerMembro.addActionListener(new SistemaTorneioRemoverDaEquipeController(sistema, this));
        adicionarMembro.addActionListener(new SistemaTorneioAdicionarAEquipeController(sistema, this));

        //adicionando as ações do menu pesquisa
        pesquisarEquipe.addActionListener(new SistemaTorneioPesquisarEquipePeloNomeController(sistema, this));
        pesquisarJogador.addActionListener(new SistemaTorneioPesquisarJogadorPorNickController(sistema, this));
        pesquisarTodosJogadores.addActionListener(new SistemaTorneioListaJogadoresController(sistema, this));


        //adicionando opções na barra de menu
        barraMenu.add(menuJogador);
        barraMenu.add(menuEquipe);
        barraMenu.add(menuPesquisa);
        setJMenuBar(barraMenu);
    }

    public static void main(String[]args){
        JFrame janela = new SistemaTorneioGUI();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}
