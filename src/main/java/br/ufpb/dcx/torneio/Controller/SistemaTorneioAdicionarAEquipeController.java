package br.ufpb.dcx.torneio.Controller;
import br.ufpb.dcx.torneio.Entities.Equipe;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.EquipeCheiaException;
import br.ufpb.dcx.torneio.Exception.EquipeNaoEncontradaException;
import br.ufpb.dcx.torneio.Exception.JogadorJaTemEquipeException;
import br.ufpb.dcx.torneio.Exception.JogadorNaoEncontradoException;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SistemaTorneioAdicionarAEquipeController implements ActionListener{
    private SistemaTorneioLOL sistema;
    private JFrame janelaPrincipal;

    public SistemaTorneioAdicionarAEquipeController(SistemaTorneioLOL sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe: ");

        try{
            Equipe equipe = sistema.pesquisarEquipePeloNome(nomeEquipe);
            if(!equipe.estaCheia()){
                JOptionPane.showMessageDialog(janelaPrincipal, "Há " + (5-equipe.getListaJogadores().size()) + " vagas na equipe");
                List<Jogador> jogadoresDisponiveis = sistema.pesquisaJogadoresSemEquipe();
                if(jogadoresDisponiveis.size() != 0){
                    String [] nomesJogadoresDisponiveis = new String[jogadoresDisponiveis.size()];

                    for(int i = 0; i< nomesJogadoresDisponiveis.length; i++){
                        nomesJogadoresDisponiveis[i] = jogadoresDisponiveis.get(i).getNickName();
                    }

                    String selecionarJogadorNome = (String) JOptionPane.showInputDialog(janelaPrincipal, "Selecione o jogador que deseja inserir na equipe:",
                            "Selecione o jogador", JOptionPane.PLAIN_MESSAGE,null, nomesJogadoresDisponiveis, nomesJogadoresDisponiveis[0]);

                    Jogador jogadorSelecionado = sistema.pesquisaJogadorPorNickName(selecionarJogadorNome);

                    sistema.adicionaJogadorAEquipe(nomeEquipe, jogadorSelecionado.getTag());
                } else{
                    JOptionPane.showMessageDialog(janelaPrincipal, "Não há jogadores sem equipes no momento");
                }

            } else{
                JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Essa equipe já está cheia", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (EquipeNaoEncontradaException e) {
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Equipe não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (EquipeCheiaException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Equipe já está com o máximo de jogadores", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (JogadorNaoEncontradoException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (JogadorJaTemEquipeException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Esse jogador já está em uma equipe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
}
