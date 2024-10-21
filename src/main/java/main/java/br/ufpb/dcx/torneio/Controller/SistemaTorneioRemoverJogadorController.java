package main.java.br.ufpb.dcx.torneio.Controller;

import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import main.java.br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import main.java.br.ufpb.dcx.torneio.Exception.ObjetoNuloException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioRemoverJogadorController implements ActionListener{
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioRemoverJogadorController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String[] opcoes = {"Sim", "Não"};

        String tagJogador = JOptionPane.showInputDialog(janelaPrincipal, "Digite a tag do jogador que deseja remover:");

        try{
            Jogador jogadorRemover = sistema.pesquisarJogadorPorTag(tagJogador);

            int confirmar = JOptionPane.showOptionDialog(janelaPrincipal, "Tem certeza que deseja remover o Jogador " +
                    jogadorRemover.getNickName() + "?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[1]);

            if(confirmar == 0){
                sistema.removerJogador(tagJogador);
            } else{
                JOptionPane.showMessageDialog(janelaPrincipal, "Remoção cancelada");
                return;
            }

        } catch (JogadorNaoEncontradoException | ObjetoNuloException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
