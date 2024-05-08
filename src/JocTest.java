import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JocTest {

    @org.junit.jupiter.api.Test
    void novaPartida_torn() {
        Joc joc = new Joc();
        joc.novaPartida();
        Assertions.assertEquals(1, joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void jugar_ok() {
        Joc joc = new Joc();
        joc.novaPartida();
        joc.jugar(0, 0);
        Assertions.assertEquals('X', joc.getTaulell()[0][0]);
        Assertions.assertEquals(2, joc.getTorn());
        joc.jugar(0, 1);
        Assertions.assertEquals('O', joc.getTaulell()[0][1]);
        Assertions.assertEquals(1, joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void jugadaGuanyadora_test() {
        Joc joc = new Joc();
        char[][] taulell = new char[3][3];
        joc.novaPartida();
        taulell[0][0] = 'X';
        taulell[0][1] = 'X';
        taulell[0][2] = 'X';
        Assertions.assertTrue(joc.jugadaGuanyadora(0, 2));
        taulell[0][0] = 'O';
        taulell[1][0] = 'O';
        taulell[2][0] = 'O';
        Assertions.assertTrue(joc.jugadaGuanyadora(2, 0));
        taulell[0][0] = 'X';
        taulell[1][1] = 'X';
        taulell[2][2] = 'X';
        Assertions.assertTrue(joc.jugadaGuanyadora(2, 2));
        taulell[0][2] = 'O';
        taulell[1][1] = 'O';
        taulell[2][0] = 'O';
        Assertions.assertTrue(joc.jugadaGuanyadora(2, 0));
    }

}