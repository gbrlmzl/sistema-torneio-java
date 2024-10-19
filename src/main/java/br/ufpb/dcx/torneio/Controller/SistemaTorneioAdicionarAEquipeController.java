package br.ufpb.dcx.torneio.Controller;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaTorneioAdicionarAEquipeController implements ActionListener{
    private SistemaTorneioLOL sistema;
    private JFrame janelaPrincipal;

    public SistemaTorneioAdicionarAEquipeController(SistemaTorneioLOL sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){

    }
}
