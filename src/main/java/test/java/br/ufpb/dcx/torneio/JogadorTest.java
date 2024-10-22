package test.java.br.ufpb.dcx.torneio;

import main.java.br.ufpb.dcx.torneio.Entities.ELOS;
import main.java.br.ufpb.dcx.torneio.Entities.Equipe;
import main.java.br.ufpb.dcx.torneio.Entities.Jogador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JogadorTest {


    @Test
    public void jogadorTest(){

        Jogador jogador1 = new Jogador("Fabricio", ELOS.ESMERALDA);

        assertTrue(jogador1.getNickName() == "Fabricio");

        Equipe equipe = new Equipe("Fininhos");

        assertFalse(jogador1.temEquipe());

        jogador1.setEquipe(equipe);
        assertTrue(jogador1.temEquipe());


    }
}
