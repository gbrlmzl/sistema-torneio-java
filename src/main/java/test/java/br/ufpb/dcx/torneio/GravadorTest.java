package test.java.br.ufpb.dcx.torneio;

import main.java.br.ufpb.dcx.torneio.Entities.ELOS;
import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import main.java.br.ufpb.dcx.torneio.Exception.*;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class GravadorTest {

    @Test
    //CUIDADO ESSE TESTE PODE TER CONFLITO COM DADOS ANTERIORES!

    public void testeDeGravacaoERecuperacao() throws IOException {

        SistemaTorneioLOL sistema = new SistemaTorneioLOL();

        sistema.iniciarSistema();



        Jogador jogador = new Jogador("Katarina", ELOS.BRONZE);

        try {
            sistema.cadastrarJogador(jogador);
        } catch (JogadorJaExisteException e) {
            fail("FALHA: JogadorJaExisteException");
        } catch (ObjetoNuloException e) {
            fail("FALHA: ObjetoNuloException");
        }

        try {
            sistema.criarNovaEquipe("Time");
        } catch (EquipeJaExisteException e) {
            fail("FALHA: EquipeJaExisteException");
        }

        try {
            sistema.adicionaJogadorAEquipe("Time",jogador.getTag());
        } catch (JogadorJaTemEquipeException e) {
            fail("FALHA: JogadorJaTemEquipeException");
        } catch (EquipeCheiaException e) {
            fail("FALHA: EquipeCheiaException");
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        sistema.finalizarSistema();
        //momento da gravação dos dados

        sistema.iniciarSistema();
        //momento da recuperação dos dados

        try {
            sistema.removerJogador(jogador.getTag());
        } catch (ObjetoNuloException e) {
            fail("FALHA: ObjetoNuloException");
        }

        try {
            sistema.removerEquipe("Time");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        sistema.finalizarSistema();
    }


}