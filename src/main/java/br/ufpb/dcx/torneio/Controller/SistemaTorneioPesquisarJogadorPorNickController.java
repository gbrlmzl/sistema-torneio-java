package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioPesquisarJogadorPorNickController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioPesquisarJogadorPorNickController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String nickName = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nickname do jogador: ");
        try {
            Jogador jogadorPesquisado = sistema.pesquisaJogadorPorNickName(nickName);
            if(jogadorPesquisado.getEquipe() == null) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Jogador Encontrado! \nNickName: " + jogadorPesquisado.getNickName() +
                        "\nELO: " + jogadorPesquisado.getElo());

            }else{
                JOptionPane.showMessageDialog(janelaPrincipal, "Jogador Encontrado! \nNickName: " + jogadorPesquisado.getNickName() +
                        "\nELO: " + jogadorPesquisado.getElo() +
                        "\nEquipe: " + jogadorPesquisado.getEquipe().getNomeDaEquipe());
            }
        } catch (JogadorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Jogador não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
