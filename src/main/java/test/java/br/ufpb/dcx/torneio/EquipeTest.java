package test.java.br.ufpb.dcx.torneio;

import main.java.br.ufpb.dcx.torneio.Entities.ELOS;
import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipeTest {

    @Test
    public void equipeTest(){

        Equipe equipe = new Equipe("Ruins");

        Equipe equipe2 = new Equipe("Ruins");

        assertTrue(equipe.equals(equipe2));

        equipe2.setNomeDaEquipe("Bons");

        assertFalse(equipe.equals(equipe2));

        Jogador jogador1 = new Jogador("Bitorugo", ELOS.BRONZE);
        Jogador jogador2 = new Jogador("MangaMurcha", ELOS.PRATA);
        Jogador jogador3 = new Jogador("OceanInMyMind", ELOS.OURO);
        Jogador jogador4 = new Jogador("Albedo", ELOS.DESAFIANTE);
        Jogador jogador5 = new Jogador("ICantSleep", ELOS.FERRO);

        equipe.adicionaJogador(jogador1);
        equipe.adicionaJogador(jogador2);
        equipe.adicionaJogador(jogador3);
        equipe.adicionaJogador(jogador4);
        equipe.adicionaJogador(jogador5);

        assertTrue(equipe.estaCheia());

        ArrayList<Jogador> jogadorList = new ArrayList<>();
        jogadorList.add(jogador1);
        jogadorList.add(jogador2);
        jogadorList.add(jogador3);
        jogadorList.add(jogador4);
        jogadorList.add(jogador5);

        assertTrue(equipe.getListaJogadores().equals(jogadorList));

        equipe.removeJogador(jogador1);
        assertFalse(equipe.verificaJogador(jogador1));

    }
}
