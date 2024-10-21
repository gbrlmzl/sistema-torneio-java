package main.java.br.ufpb.dcx.torneio.Controller;

import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Exception.EquipeNaoEncontradaException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioRemoverEquipeController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioRemoverEquipeController(SistemaTorneioLOL sistema, JFrame janela){
        this.janelaPrincipal = janela;
        this.sistema = sistema;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] opcoes = {"Sim", "Não"};

        String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe que deseja remover:");

        try {
            Equipe equipeRemover = sistema.pesquisarEquipePeloNome(nomeEquipe);
            int confirmar = JOptionPane.showOptionDialog(janelaPrincipal, "Tem certeza que deseja remover a equipe " +
                    nomeEquipe + "?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[1]);

            if(confirmar == 0){
                sistema.removerEquipe(nomeEquipe);
            } else{
                JOptionPane.showMessageDialog(janelaPrincipal, "Remoção cancelada");
                return;
            }

        } catch (EquipeNaoEncontradaException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Essa equipe não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
