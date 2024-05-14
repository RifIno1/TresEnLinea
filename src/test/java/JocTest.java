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

    @org.junit.jupiter.api.Test
        // un taulell en blanc , all false
    void jugadaGuanyadora(){
        Joc joc = new Joc();
        joc.novaPartida();
        for (int i = 0; i < joc.getTaulell().length; i++) {
            for (int j = 0; j < joc.getTaulell().length; j++) {
                Assertions.assertFalse(joc.jugadaGuanyadora(i, j));
            }
        }
    }


    @org.junit.jupiter.api.Test
    void jugadaGuanyadora_taulellAmbUnaFitxa(){
        Joc joc = new Joc();
        joc.novaPartida();
        joc.jugar(0, 0);
        for (int i = 0; i < joc.getTaulell().length; i++) {
            for (int j = 0; j < joc.getTaulell().length; j++) {
                    Assertions.assertFalse(joc.jugadaGuanyadora(i, j));
            }
        }
    }

    // guanyar al jugador 1 y jugador 2.
    @org.junit.jupiter.api.Test
    void jugadaGuanyadora_taulellAmbFitxes(){

        
        Joc joc = new Joc();
        // jugador 1
        joc.novaPartida();
        joc.jugar(0, 0);
        joc.jugar(0, 1);
        joc.jugar(1, 0);
        joc.jugar(1, 1);
        joc.jugar(2, 0);
        Assertions.assertTrue(joc.jugadaGuanyadora(0, 0));
        Assertions.assertFalse(joc.jugadaGuanyadora(0, 1));
        Assertions.assertTrue(joc.jugadaGuanyadora(1, 0));
        Assertions.assertFalse(joc.jugadaGuanyadora(1, 1));
        Assertions.assertTrue(joc.jugadaGuanyadora(2, 0));



        // jugador 2
        joc.novaPartida();
        joc.jugar(0, 0);
        joc.jugar(0, 1);
        joc.jugar(1, 0);
        joc.jugar(1, 1);
        joc.jugar(2, 2);
        joc.jugar(2, 1);
        Assertions.assertFalse(joc.jugadaGuanyadora(0, 0));
        Assertions.assertTrue(joc.jugadaGuanyadora(0, 1));
        Assertions.assertFalse(joc.jugadaGuanyadora(1, 0));
        Assertions.assertTrue(joc.jugadaGuanyadora(1, 1));
        Assertions.assertFalse(joc.jugadaGuanyadora(2, 2));
        Assertions.assertTrue(joc.jugadaGuanyadora(2, 1));
        
        




    }





}


