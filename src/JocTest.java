import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JocTest {

    @org.junit.jupiter.api.Test
    void novaPartida_torn() {
        Joc joc = new Joc();
        joc.novaPartida(new char[3][3]);
        Assertions.assertEquals(1,joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void jugar() {
        Joc joc = new Joc();
        char[][] tauler = new char[3][3];
        joc.novaPartida(tauler);
        joc.jugar(tauler,0,0);
        Assertions.assertEquals('X',tauler[0][0]);
        Assertions.assertEquals(2,joc.getTorn());
        joc.jugar(tauler,0,1);
        Assertions.assertEquals('O',tauler[0][1]);
        Assertions.assertEquals(1,joc.getTorn());
    }

    @org.junit.jupiter.api.Test
    void jugadaGuanyadora() {
        Joc joc = new Joc();
        char[][] tauler = new char[3][3];
        joc.novaPartida(tauler);
        tauler[0][0] = 'X';
        tauler[0][1] = 'X';
        tauler[0][2] = 'X';
        Assertions.assertTrue(joc.jugadaGuanyadora(tauler,'X'));
        tauler[0][0] = 'O';
        tauler[0][1] = 'O';
        tauler[0][2] = 'O';
        Assertions.assertTrue(joc.jugadaGuanyadora(tauler,'O'));
        tauler[0][0] = 'X';
        tauler[1][0] = 'X';
        tauler[2][0] = 'X';
        Assertions.assertTrue(joc.jugadaGuanyadora(tauler,'X'));
    }

}