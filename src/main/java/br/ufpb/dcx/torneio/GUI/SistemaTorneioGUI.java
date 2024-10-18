package br.ufpb.dcx.torneio.GUI;

import br.ufpb.dcx.torneio.Controller.SistemaTorneioCadastrarJogadorController;
import br.ufpb.dcx.torneio.Controller.SistemaTorneioCriarNovaEquipeController;
import br.ufpb.dcx.torneio.entitie.Equipe;
import br.ufpb.dcx.torneio.entitie.Jogador;
import br.ufpb.dcx.torneio.system.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        JMenu menuJogador = new JMenu("Jogador");
        JMenuItem cadastrarJogador = new JMenuItem("Cadastrar Jogador");
        menuJogador.add(cadastrarJogador);

        JMenu menuEquipe = new JMenu("Equipe");
        JMenuItem cadastrarEquipe = new JMenuItem("Cadastrar Equipe");
        menuEquipe.add(cadastrarEquipe);

        //adicionando as ações
        cadastrarJogador.addActionListener(new SistemaTorneioCadastrarJogadorController(sistema, this));

        cadastrarEquipe.addActionListener(new SistemaTorneioCriarNovaEquipeController(sistema, this));

        //adicionando opções na barra de menu
        barraMenu.add(menuJogador);
        barraMenu.add(menuEquipe);
    }

    
}
