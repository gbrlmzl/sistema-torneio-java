package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SistemaTorneioListaJogadoresController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioListaJogadoresController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        try {
            Collection<Jogador> listaJogadores = sistema.listaJogadores();
            for(Jogador j : listaJogadores){
                if(j.getEquipe() == null){
                    JOptionPane.showMessageDialog(janelaPrincipal, "Nickname: " + j.getNickName() +
                            "\nELO: " + j.getElo());
                }else{
                    JOptionPane.showMessageDialog(janelaPrincipal, "Nickname: " + j.getNickName() +
                            "\nELO: " + j.getElo() +
                            "\nEquipe: " + j.getEquipe().getNomeDaEquipe());

                }
            }
        } catch (JogadorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Não há jogadores cadastrados", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
