package br.ufpb.dcx.torneio.Controller;
import br.ufpb.dcx.torneio.entitie.ELOS;
import br.ufpb.dcx.torneio.system.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class SistemaTorneioAdicionarAEquipeController implements ActionListener{
    private SistemaTorneioLOL sistema;
    private JFrame janelaPrincipal;

    public SistemaTorneioAdicionarAEquipeController(SistemaTorneioLOL sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        new JanelaCadastroJogador();

    }

    public class JanelaCadastroJogador extends JDialog{

        private ELOS eloSelecionado = null;

        public JanelaCadastroJogador(){
            setTitle("Cadastrar Jogador");
            setSize(1100, 600);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            //DO D L

            JLabel nickName = new JLabel("NickName:");
            JTextField nickNameUsuario = new JTextField(20);

            JLabel elo = new JLabel("ELO: ");
            JTextField eloUsuario = new JTextField(20);

            JPanel painelCampos = new JPanel();
            painelCampos.setLayout(new GridLayout(2,2));
            painelCampos.add(nickName);
            painelCampos.add(nickNameUsuario);

            add(painelCampos, BorderLayout.NORTH);

            JPanel painelELO = new JPanel();
            painelELO.setLayout(new GridLayout(2,6));

            Map<ELOS, String> mapELOSImagem = new HashMap<>();

            mapELOSImagem.put(ELOS.UNRANKED, "unranked.png");
            mapELOSImagem.put(ELOS.FERRO, "ferro.png");
            mapELOSImagem.put(ELOS.BRONZE, "bronze.png");
            mapELOSImagem.put(ELOS.PRATA, "prata.png");
            mapELOSImagem.put(ELOS.OURO, "ouro.png");
            mapELOSImagem.put(ELOS.PLATINA, "platina.png");
            mapELOSImagem.put(ELOS.ESMERALDA, "esmeralda.png");
            mapELOSImagem.put(ELOS.DIAMANTE, "diamante.png");
            mapELOSImagem.put(ELOS.MESTRE, "mestre.png");
            mapELOSImagem.put(ELOS.GRAO_MESTRE, "graoMestre.png");
            mapELOSImagem.put(ELOS.DESAFIANTE, "desafiante.png");


            for(ELOS e : mapELOSImagem.keySet()){
                String imagem = mapELOSImagem.get(e);
                JLabel labelELO = new JLabel(new ImageIcon(imagem));
                labelELO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                labelELO.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent m){
                        ELOS eloSelecionado = e;
                        JOptionPane.showMessageDialog(null, "ELO: " + e + " selecionado");
                    }
                });

                painelELO.add(labelELO);
            }

            add(painelELO, BorderLayout.CENTER);

            JButton salvarButton = new JButton("Salvar");
            salvarButton.addActionListener(b -> {
                if (eloSelecionado != null){
                    JOptionPane.showMessageDialog(null, "Jogador salvo com ELO: " + eloSelecionado);
                } else{
                    JOptionPane.showMessageDialog(null, "Por favor, selecione seu ELO");
                }
            });

            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.add(nickName);
            painel.add(nickNameUsuario);

            painel.add(elo);
            painel.add(eloUsuario);
            painel.add(salvarButton);

            add(painel);

            setVisible(true);


        }
    }
}
