package main.java.br.ufpb.dcx.torneio.Controller;

import main.java.br.ufpb.dcx.torneio.Exception.EquipeJaExisteException;
import main.java.br.ufpb.dcx.torneio.Exception.JogadorNaoEstaNaEquipeException;
import main.java.br.ufpb.dcx.torneio.Exception.ObjetoNuloException;
import main.java.br.ufpb.dcx.torneio.Exception.OperacaoCanceladaException;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioCriarNovaEquipeController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioCriarNovaEquipeController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        //CRIAR EQUIPE = NOME, LISTA DE JOGADORES, PONTUACAO
        String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe:");

        try {
            if(nomeEquipe == null) throw new OperacaoCanceladaException("Operação cancelada");


            sistema.criarNovaEquipe(nomeEquipe);
        } catch (EquipeJaExisteException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Essa equipe já está cadastrada", "ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(ObjetoNuloException e){
            JOptionPane.showMessageDialog(janelaPrincipal,e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }catch(OperacaoCanceladaException e){
            JOptionPane.showMessageDialog(janelaPrincipal,e.getMessage(),"",JOptionPane.INFORMATION_MESSAGE);

        }

    }
}
