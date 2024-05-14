import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class JocTest {

    @org.junit.jupiter.api.Test
    void novaPartida_torn() {
        Joc joc = new Joc();
        joc.novaPartida();
        Assertions.assertEquals(1, joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void novaPartida_torn_new() {
        // github
        Joc joc = new Joc();
        joc.novaPartida();
        Assertions.assertEquals(1, joc.getTorn());
    }



    @org.junit.jupiter.api.Test
    void jugar_fitxa_jugador1_en_taulellBuit() {
        Joc joc = new Joc();
        joc.novaPartida();
        joc.jugar(0, 0);
        char[][] taulell = joc.getTaulell();
        Assertions.assertEquals('X', taulell[0][0]);
        Assertions.assertEquals(2, joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void jugar_fitxa_jugador2_en_taulellAmbFitxa() {
        Joc joc = new Joc();
        joc.novaPartida();
        joc.jugar(0, 0);
        joc.jugar(0, 1);
        char[][] taulell = joc.getTaulell();
        Assertions.assertEquals('O', taulell[0][1]);
        Assertions.assertEquals(1, joc.getTorn());
    }


    @org.junit.jupiter.api.Test
    void jugar_noPosarFitxa_en_posicioJaOcupada() {
        Joc joc = new Joc();
        joc.novaPartida();
        joc.jugar(0, 0);
        joc.jugar(0, 0);
        char[][] taulell = joc.getTaulell();
        Assertions.assertEquals('X', taulell[0][0]);
        Assertions.assertEquals(2, joc.getTorn());
    }

    // methode test for :Starting with a blank board: Ensure that the method call returns "false" for all positions.
    @org.junit.jupiter.api.Test
    void jugadaGuanyadora_taulellBuit() {
        Joc joc = new Joc();
        joc.novaPartida();
        for (int i = 0; i < joc.getTaulell().length; i++) {
            for (int j = 0; j < joc.getTaulell().length; j++) {
                Assertions.assertTrue(joc.jugadaGuanyadora(i, j));
            }
        }
    }



}