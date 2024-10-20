package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.NenhumaEquipeCadastradaException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SistemaTorneioListaEquipesController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioListaEquipesController(SistemaTorneioLOL sistema, JFrame janela){
        this.janelaPrincipal = janela;
        this.sistema = sistema;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Collection<Equipe> todasEquipes = sistema.listaEquipes();
            for(Equipe e : todasEquipes){

                JOptionPane.showMessageDialog(janelaPrincipal,"Nome da equipe: " + e.getNomeDaEquipe() +
                        "\nJogadores: " + e.getListaJogadores() + //TODO
                        "\nPontuação: " + e.getPontuacao());      //TODO

            }
        } catch (NenhumaEquipeCadastradaException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Nenhuma equipe foi cadastrada", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
