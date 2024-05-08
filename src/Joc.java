public class Joc {

    private char [][] taulell;
    private int torn;

    public char[][] getTaulell() {
        return taulell;
    }
    public int getTorn() {
        return torn;
    }

    public void novaPartida()
    {
        taulell = new char[3][3];
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell[i].length; j++) {
                taulell[i][j] = ' ';
            }
        }
        torn = 1;
    }

    public void CarregarPartida() {
        throw new UnsupportedOperationException();
    }
    
    public void jugar(int fila, int columna) {
        if (taulell[fila][columna] == ' ') {
            if (torn == 1) {
                taulell[fila][columna] = 'X';
                torn = 2;
            } else {
                taulell[fila][columna] = 'O';
                torn = 1;
            }
        }
    }

    public boolean jugadaGuanyadora(int fila, int columna) {
        char jugador = taulell[fila][columna];
        if (taulell[fila][0] == jugador && taulell[fila][1] == jugador && taulell[fila][2] == jugador) {
            return true;
        }
        if (taulell[0][columna] == jugador && taulell[1][columna] == jugador && taulell[2][columna] == jugador) {
            return true;
        }
        if (fila == columna) {
            if (taulell[0][0] == jugador && taulell[1][1] == jugador && taulell[2][2] == jugador) {
                return true;
            }
        }
        if (fila + columna == 2) {
            if (taulell[0][2] == jugador && taulell[1][1] == jugador && taulell[2][0] == jugador) {
                return true;
            }
        }
        return false;
    }

}
