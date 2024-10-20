package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Exception.EquipeNaoEncontradaException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioPesquisarEquipePeloNomeController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioPesquisarEquipePeloNomeController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String[] opcoes = {"Sim", "Não"};
        boolean repetidor = true;

        while(repetidor){

        String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe: ");
            try {
                Equipe equipePesquisada = sistema.pesquisarEquipePeloNome(nomeEquipe);
                JOptionPane.showMessageDialog(janelaPrincipal,"Equipe encontrada! \nNome da equipe: " + equipePesquisada.getNomeDaEquipe() +
                        "\nJogadores: " + equipePesquisada.getListaJogadores() +
                        "\nPontuação: " + equipePesquisada.getPontuacao());

            } catch (EquipeNaoEncontradaException e) {
                int continuar = JOptionPane.showOptionDialog(janelaPrincipal, "ERRO: Equipe não está cadastrada. Deseja tentar novamente?",
                        null,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,opcoes,opcoes[1]);
                if(continuar == 1){
                    JOptionPane.showMessageDialog(janelaPrincipal,"Pesquisa cancelada");
                    return;
                }
            }

        }
    }
}
