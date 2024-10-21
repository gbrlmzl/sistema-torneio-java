package main.java.br.ufpb.dcx.torneio.GUI;

import main.java.br.ufpb.dcx.torneio.Controller.*;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;
import main.java.br.ufpb.dcx.torneio.Controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SistemaTorneioGUI extends JFrame{

    SistemaTorneioLOL sistema= new SistemaTorneioLOL();
    JMenuBar barraMenu = new JMenuBar();
    private BufferedImage imagemFundo;

    public SistemaTorneioGUI() throws IOException {
        try{
            String imagemArquivo = "src/main/java/main/java/br/ufpb/dcx/torneio/resources/sistemaTorneio.jpg";
            imagemFundo = ImageIO.read(new File(imagemArquivo));

            int largura = imagemFundo.getWidth();
            int altura = imagemFundo.getHeight();

            setSize(largura, altura);

            setTitle("Torneio");

            setLocation(150, 150);
            setResizable(false);
            setBackground(Color.lightGray);

            JPanel painelImagem = new JPanel() {
                private Image imagemFundo;
                {
                    try{
                        imagemFundo = ImageIO.read(new File(imagemArquivo));
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    if(imagemFundo != null){
                        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            setContentPane(painelImagem);
            add(new JLabel());

            JMenu menuCadastro = new JMenu("Cadastro");
            JMenuItem cadastrarJogador = new JMenuItem("Cadastrar Jogador");
            JMenuItem cadastrarEquipe = new JMenuItem("Cadastrar Equipe");
            menuCadastro.add(cadastrarJogador);
            menuCadastro.add(cadastrarEquipe);


            JMenu menuRemocao = new JMenu("Remover");
            JMenuItem removerJogador = new JMenuItem("Remover Jogador");
            JMenuItem removerEquipe = new JMenuItem("Remover Equipe");
            menuRemocao.add(removerJogador);
            menuRemocao.add(removerEquipe);


            JMenu menuJogador = new JMenu("Jogador");
            JMenuItem listaJogadores = new JMenuItem("Listar todos os jogadores");
            JMenuItem pesquisarJogador = new JMenuItem("Pesquisar Jogador");
            JMenuItem consultarTagJogador = new JMenuItem("Consultar Tag do jogador");
            menuJogador.add(listaJogadores);
            menuJogador.add(pesquisarJogador);
            menuJogador.add(consultarTagJogador);


            JMenu menuEquipe = new JMenu("Gerenciamento de equipe");
            JMenuItem adicionarAEquipe = new JMenuItem("Adicionar novo jogador na equipe");
            JMenuItem removerDaEquipe = new JMenuItem("Remover jogador da equipe");
            JMenuItem pesquisarEquipe = new JMenuItem("Pesquisar Equipe");
            JMenuItem listaEquipes = new JMenuItem("Listar todas as equipes");
            menuEquipe.add(pesquisarEquipe);
            menuEquipe.add(adicionarAEquipe);
            menuEquipe.add(removerDaEquipe);
            menuEquipe.add(listaEquipes);

            //adicionando as ações
            cadastrarJogador.addActionListener(new SistemaTorneioCadastrarJogadorController(sistema, this));
            cadastrarEquipe.addActionListener(new SistemaTorneioCriarNovaEquipeController(sistema, this));

            removerJogador.addActionListener(new SistemaTorneioRemoverJogadorController(sistema, this));
            removerEquipe.addActionListener(new SistemaTorneioRemoverEquipeController(sistema, this));

            listaJogadores.addActionListener(new SistemaTorneioListaJogadoresController(sistema, this));
            pesquisarJogador.addActionListener(new SistemaTorneioPesquisarJogadorPorNickController(sistema, this));
            consultarTagJogador.addActionListener(new SistemaTorneioConsultarTagController(sistema, this));

            adicionarAEquipe.addActionListener(new SistemaTorneioAdicionarAEquipeController(sistema, this));
            removerDaEquipe.addActionListener(new SistemaTorneioRemoverDaEquipeController(sistema, this));
            pesquisarEquipe.addActionListener(new SistemaTorneioPesquisarEquipePeloNomeController(sistema, this));
            listaEquipes.addActionListener(new SistemaTorneioListaEquipesController(sistema, this));

            //adicionando opções na barra de menu
            barraMenu.add(menuCadastro);
            barraMenu.add(menuRemocao);
            barraMenu.add(menuJogador);
            barraMenu.add(menuEquipe);
            setJMenuBar(barraMenu);


            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we){
                    try {
                        sistema.finalizarSistema();
                        JOptionPane.showMessageDialog(SistemaTorneioGUI.this, "Os dados foram salvos");
                        System.exit(0);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(SistemaTorneioGUI.this, "ERRO: Ocorreu um erro no salvamento dos dados", "ERRO", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            });

            setVisible(true);

            try{
                sistema.iniciarSistema();
                JOptionPane.showMessageDialog(this, "Os dados foram recuperados");
            }catch(IOException e){
                JOptionPane.showMessageDialog(this, "ERRO: Ocorreu um erro durante a recuperação dos dados", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[]args) throws IOException {
        SistemaTorneioGUI janela = new SistemaTorneioGUI();

    }

    
}
