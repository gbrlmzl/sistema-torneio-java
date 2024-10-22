import br.ufpb.dcx.torneio.Entities.ELOS;
import br.ufpb.dcx.torneio.Entities.Jogador;
import br.ufpb.dcx.torneio.Exception.*;
import br.ufpb.dcx.torneio.System.SistemaTorneioLOL;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
            sistema.adicionaJogadorAEquipe("Time", jogador.getTag());
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
    }

    @Test
    public void recuperaDadosTest(){

        SistemaTorneioLOL sistema = new SistemaTorneioLOL();

        try {
            sistema.iniciarSistema();
        } catch (IOException e) {
            fail("FALHA: IOException");
        }

        Jogador jogador = null;
        try {
            jogador = sistema.pesquisaJogadorPorNickName("Katarina");
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        }

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

        try {
            sistema.finalizarSistema();
        } catch (IOException e) {
            fail("FALHA: IOException");
        }

        File contador = new File("contador.dat");
        File jogadores = new File("jogadores.dat");
        File equipes = new File("equipes.dat");
        contador.delete();
        jogadores.delete();
        equipes.delete();
    }


}