package main.java.br.ufpb.dcx.torneio.Controller;

import main.java.br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioConsultarTagController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioConsultarTagController(SistemaTorneioLOL sistema, JFrame janela){
        this.janelaPrincipal = janela;
        this.sistema = sistema;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] opcoes = {"Sim", "Não"};
        boolean repetidor = true;

        while(repetidor){
            String nickNameJogador = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nickName do jogador que deseja consultar a Tag:");
            try{
                String tag = sistema.consultarTagJogador(nickNameJogador);
                JOptionPane.showMessageDialog(janelaPrincipal, "Tag de " + nickNameJogador + ": " + tag);
                return;
            } catch (JogadorNaoEncontradoException e) {
                int continuar = JOptionPane.showOptionDialog(janelaPrincipal, "ERRO: Jogador não está cadastrado. Deseja tentar novamente?",
                        "ERRO",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,null,opcoes,opcoes[1]);

                if(continuar == 1){
                    JOptionPane.showMessageDialog(janelaPrincipal, "Consulta cancelada");
                    repetidor = false;
                    return;
                }
            }
        }
    }
}
