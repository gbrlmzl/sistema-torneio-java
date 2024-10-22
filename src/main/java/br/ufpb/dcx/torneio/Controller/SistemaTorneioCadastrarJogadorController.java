package br.ufpb.dcx.torneio.Controller;


import br.ufpb.dcx.torneio.Entities.ELOS;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.*;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SistemaTorneioCadastrarJogadorController implements ActionListener {
    private JFrame janelaPrincipal;
    private SistemaTorneioLOL sistema;

    public SistemaTorneioCadastrarJogadorController(SistemaTorneioLOL sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        //Jogador = nickname, elo, equipe, tag(gerada)
        String[] opcoes = {"Sim", "Não"};

        //NickName do jogador
        String nickNameJogador = JOptionPane.showInputDialog(janelaPrincipal,"Digite seu NickName:");


        //Elo do jogador
        String[] elosString = {"Sem ranque", "Ferro", "Bronze", "Prata", "Ouro", "Platina", "Diamante",
                "Mestre", "Grão-Mestre", "Desafiante"};

        String eloSelecionadoString = (String) JOptionPane.showInputDialog(janelaPrincipal,"Selecione seu rank:",
                "ELO do jogador", JOptionPane.PLAIN_MESSAGE, null, elosString, elosString[0]);
        ELOS eloSelecionado;

        if(eloSelecionadoString.equals("Sem ranque")){
            eloSelecionado = ELOS.UNRANKED;
        }else if(eloSelecionadoString.equals("Ferro")){
            eloSelecionado = ELOS.FERRO;
        }else if(eloSelecionadoString.equals("Bronze")){
            eloSelecionado = ELOS.BRONZE;
        }else if(eloSelecionadoString.equals("Prata")){
            eloSelecionado = ELOS.PRATA;
        }else if(eloSelecionadoString.equals("Ouro")){
            eloSelecionado = ELOS.OURO;
        }else if(eloSelecionadoString.equals("Platina")){
            eloSelecionado = ELOS.PLATINA;
        }else if(eloSelecionadoString.equals("Esmeralda")){
            eloSelecionado = ELOS.ESMERALDA;
        }else if(eloSelecionadoString.equals("Diamante")){
            eloSelecionado = ELOS.DIAMANTE;
        }else if(eloSelecionadoString.equals("Mestre")){
            eloSelecionado = ELOS.MESTRE;
        }else if(eloSelecionadoString.equals("Grão-Mestre")){
            eloSelecionado = ELOS.GRAO_MESTRE;
        }else{
            eloSelecionado = ELOS.DESAFIANTE;
        }

        Jogador jogadorElemento = new Jogador(nickNameJogador, eloSelecionado);

        try{
            sistema.cadastrarJogador(jogadorElemento);
            JOptionPane.showMessageDialog(janelaPrincipal, "Tag do jogador " + jogadorElemento.getNickName() + ": " + jogadorElemento.getTag() +
                    "\n Jogador cadastrado com sucesso");
            //Possui equipe?
            int confirmacaoEquipe = JOptionPane.showOptionDialog(janelaPrincipal, jogadorElemento.getNickName() + " já possui Equipe?",null,JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null, opcoes, opcoes[1]);

            if(confirmacaoEquipe == 0){
                String nomeEquipe = JOptionPane.showInputDialog(janelaPrincipal, "Digite o nome da equipe: ");
                sistema.adicionaJogadorAEquipe(nomeEquipe, jogadorElemento.getTag());

                }else{
                return;
            }

        }catch(JogadorJaExisteException e){
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Esse jogador já existe.","ERRO", JOptionPane.ERROR_MESSAGE);
        }catch (ObjetoNuloException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "ERRO: Os dados não foram preenchidos corretamente","ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(EquipeNaoEncontradaException e){
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Equipe não existe","ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(EquipeCheiaException e){
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Equipe já está cheia","ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(JogadorJaTemEquipeException e){
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Esse jogador já possui equipe","ERRO", JOptionPane.ERROR_MESSAGE);
        }catch(JogadorNaoEncontradoException e){
            JOptionPane.showMessageDialog(janelaPrincipal,"ERRO: Esse jogador não existe","ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
