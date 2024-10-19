package br.ufpb.dcx.torneio.Controller;

import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SistemaTorneioCadastrarJogadorController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioCadastrarJogadorController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){

    }
}
