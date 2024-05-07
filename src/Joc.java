public class Joc {

    int torn;

    public int getTorn() {
        return torn;
    }

    public void novaPartida(char[][] tauler) {
        torn = 1;
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                tauler[i][j] = ' ';
            }
        }
    }

    public void CarregarPartida() {
        throw new UnsupportedOperationException();
    }
    
    public void jugar(char[][] tauler, int fila, int columna) {
        if (torn == 1) {
            tauler[fila][columna] = 'X';
            torn = 2;
        } else {
            tauler[fila][columna] = 'O';
            torn = 1;
        }
    }

    public boolean jugadaGuanyadora(char[][] tauler, char jugador) {
        for (int i = 0; i < tauler.length; i++) {
            if (tauler[i][0] == jugador && tauler[i][1] == jugador && tauler[i][2] == jugador) {
                return true;
            }
        }
        for (int i = 0; i < tauler.length; i++) {
            if (tauler[0][i] == jugador && tauler[1][i] == jugador && tauler[2][i] == jugador) {
                return true;
            }
        }
        if (tauler[0][0] == jugador && tauler[1][1] == jugador && tauler[2][2] == jugador) {
            return true;
        }
        if (tauler[0][2] == jugador && tauler[1][1] == jugador && tauler[2][0] == jugador) {
            return true;
        }
        return false;
    }



}
