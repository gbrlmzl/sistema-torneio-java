package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.EquipeNaoEncontradaException;
import br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import br.ufpb.dcx.torneio.Exception.JogadorNaoEstaNaEquipeException;
import br.ufpb.dcx.torneio.Exception.JogadorNaoTemEquipeException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioRemoverDaEquipeController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioRemoverDaEquipeController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String[] opcoes = {"Sim", "Não"};

        String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe: ");

        String tagJogador = JOptionPane.showInputDialog(janelaPrincipal, "Digite a tag do jogador que deseja remover da equipe:");

        try {
            Jogador jogador = sistema.pesquisarJogadorPorTag(tagJogador);

            int confirmar = JOptionPane.showOptionDialog(janelaPrincipal, "Tem certeza que deseja remover o jogador " +
                    jogador.getNickName() + "?",null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,opcoes,opcoes[1]);

            if(confirmar == 0){
                try {
                    sistema.removerDaEquipe(tagJogador, nomeEquipe);
                    JOptionPane.showMessageDialog(janelaPrincipal,"Jogador removido com sucesso");
                } catch (JogadorNaoTemEquipeException e) {
                    JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador não possui equipe para ser removido", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (JogadorNaoEstaNaEquipeException e) {
                    JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador não está na equipe " + nomeEquipe, "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (JogadorNaoEncontradoException e) {
                    JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Esse jogador não existe", "ERRO", JOptionPane.ERROR_MESSAGE);

                } catch (EquipeNaoEncontradaException e) {
                    JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Essa equipe não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(janelaPrincipal, "Remoção cancelada");
                return;
            }

        } catch (JogadorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }




    }
}
