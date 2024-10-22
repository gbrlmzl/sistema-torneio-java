package test.java.br.ufpb.dcx.torneio;

import main.java.br.ufpb.dcx.torneio.Entities.ELOS;
import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import main.java.br.ufpb.dcx.torneio.Exception.*;
import org.junit.jupiter.api.Test;
import main.java.br.ufpb.dcx.torneio.System.SistemaTorneioLOL;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class SistemaTest {

    @Test
    public void sistemaJogadoresTest(){

        SistemaTorneioLOL sistema = new SistemaTorneioLOL();


        Jogador jogador1 = new Jogador("Zed", ELOS.DESAFIANTE);

        try {
            sistema.cadastrarJogador(jogador1);
        } catch (JogadorJaExisteException e) {
            fail("FALHA: JogadorJaExisteException");
        } catch (ObjetoNuloException e) {
            fail("FALHA: ObjetoNuloException");
        }

        Jogador jogador2 = new Jogador("Chico", ELOS.ESMERALDA);

        try {
            sistema.cadastrarJogador(jogador2);
        } catch (JogadorJaExisteException e) {
            fail("FALHA: JogadorJaExisteException");
        } catch (ObjetoNuloException e) {
            fail("FALHA: ObjetoNuloException");
        }

        try {
             System.out.println(sistema.listaJogadores());
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        }

        try {
            assertTrue(sistema.consultarTagJogador("Zed")== jogador1.getTag());
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        }

        try {
            assertTrue(sistema.pesquisaJogadorPorNickName("Zed")==jogador1);
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        }

        assertTrue(sistema.pesquisaJogadoresSemEquipe().contains(jogador1));
        assertTrue(sistema.pesquisaJogadoresSemEquipe().contains(jogador2));

        try {
            sistema.criarNovaEquipe("Time");
        } catch (EquipeJaExisteException e) {
            fail("FALHA: EquipeJaExisteException");
        }

        try {
            sistema.adicionaJogadorAEquipe("Time", jogador1.getTag());
        } catch (JogadorJaTemEquipeException e) {
            fail("FALHA: JogadorJaTemEquipeException");
        } catch (EquipeCheiaException e) {
            fail("FALHA: EquipeCheiaException");
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        assertFalse(sistema.pesquisaJogadoresSemEquipe().contains(jogador1));
        assertTrue(sistema.pesquisaJogadoresSemEquipe().contains(jogador2));

        try {
            assertTrue(sistema.pesquisarJogadorPorTag(jogador1.getTag())==jogador1);
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        }
    }

    @Test
    public void sistemaEquipeTest(){

        SistemaTorneioLOL sistema = new SistemaTorneioLOL();

        Jogador jogador1 = new Jogador("Bitorugo", ELOS.BRONZE);
        Jogador jogador2 = new Jogador("MangaMurcha", ELOS.PRATA);
        Jogador jogador3 = new Jogador("OceanInMyMind", ELOS.OURO);
        Jogador jogador4 = new Jogador("Albedo", ELOS.DESAFIANTE);
        Jogador jogador5 = new Jogador("ICantSleep", ELOS.FERRO);
        Jogador jogador6 = new Jogador("MonoLux", ELOS.DIAMANTE);

        try {
            sistema.cadastrarJogador(jogador1);
            sistema.cadastrarJogador(jogador2);
            sistema.cadastrarJogador(jogador3);
            sistema.cadastrarJogador(jogador4);
            sistema.cadastrarJogador(jogador5);
            sistema.cadastrarJogador(jogador6);
        } catch (JogadorJaExisteException e) {
            fail("FALHA: JogadorJaExisteException");
        } catch (ObjetoNuloException e) {
            fail("FALHA: ObjetoNuloException");
        }

        try {
            sistema.criarNovaEquipe("Amo Abacaxi");
            sistema.criarNovaEquipe("Amo Manga");
        } catch (EquipeJaExisteException e) {
            fail("FALHA: EquipeJaExisteException");
        }

        assertTrue(sistema.pesquisaJogadoresSemEquipe().size()==6);

        try {

            sistema.adicionaJogadorAEquipe("Amo Manga", jogador1.getTag());
            sistema.adicionaJogadorAEquipe("Amo Abacaxi", jogador2.getTag());
            sistema.adicionaJogadorAEquipe("Amo Manga", jogador3.getTag());
            sistema.adicionaJogadorAEquipe("Amo Abacaxi", jogador4.getTag());
            sistema.adicionaJogadorAEquipe("Amo Manga", jogador5.getTag());
            sistema.adicionaJogadorAEquipe("Amo Abacaxi", jogador6.getTag());

        } catch (JogadorJaTemEquipeException e) {
            fail("FALHA: JogadorJaTemEquipeException");
        } catch (EquipeCheiaException e) {
            fail("FALHA: EquipeCheiaException");
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        assertTrue(sistema.pesquisaJogadoresSemEquipe().size()==0);

        try {
            System.out.println(sistema.listaEquipes());
        } catch (NenhumaEquipeCadastradaException e) {
            fail("FALHA: NenhumaEquipeCadastradaException");
        }

        try {
            assertTrue(sistema.listaEquipes().contains(sistema.pesquisarEquipePeloNome("Amo Abacaxi")));
            assertTrue(sistema.listaEquipes().contains(sistema.pesquisarEquipePeloNome("Amo Manga")));
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        } catch (NenhumaEquipeCadastradaException e) {
            fail("FALHA: NenhumaEquipeCadastradaException");
        }

        try {
            sistema.removerDaEquipe(jogador1.getTag(), jogador1.getEquipe().getNomeDaEquipe());
            sistema.removerDaEquipe(jogador2.getTag(), jogador2.getEquipe().getNomeDaEquipe());
            sistema.removerDaEquipe(jogador3.getTag(), jogador3.getEquipe().getNomeDaEquipe());
        } catch (JogadorNaoTemEquipeException e) {
            fail("FALHA: JogadorNaoTemEquipeException");
        } catch (JogadorNaoEstaNaEquipeException e) {
            fail("FALHA: JogadorNaoEstaNaEquipeException");
        } catch (JogadorNaoEncontradoException e) {
            fail("FALHA: JogadorNaoEncontradoException");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        assertTrue(sistema.pesquisaJogadoresSemEquipe().size()==3);

        try {
            sistema.removerEquipe("Amo Manga");
        } catch (EquipeNaoEncontradaException e) {
            fail("FALHA: EquipeNaoEncontradaException");
        }

        try {
            sistema.pesquisarEquipePeloNome("Amo Manga");
            fail("A equipe não pode existir e tem q cair no cath");
        } catch (EquipeNaoEncontradaException e) {
            //deu bom
        }
    }
}
