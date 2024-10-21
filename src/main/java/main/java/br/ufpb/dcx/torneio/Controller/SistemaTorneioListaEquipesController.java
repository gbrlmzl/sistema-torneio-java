package main.java.br.ufpb.dcx.torneio.Controller;

import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import main.java.br.ufpb.dcx.torneio.Exception.NenhumaEquipeCadastradaException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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
                StringBuilder mensagem = new StringBuilder(
                        "Equipe: " + e.getNomeDaEquipe() + "\nJogadores:\n");
                for(Jogador jogador : e.getListaJogadores()){
                    mensagem.append("- ").append(jogador.getNickName()).append("\n");
                }
                JOptionPane.showMessageDialog(janelaPrincipal,"Equipe encontrada!\n" + mensagem.toString());
            }

        } catch (NenhumaEquipeCadastradaException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Nenhuma equipe foi cadastrada", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
