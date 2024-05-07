public class Tui {

    public void mostrarMenu() {
        System.out.println("1. Nova Partida");
        System.out.println("2. Carregar Partida");
        System.out.println("3. Configuració");
        System.out.println("4. Sortir");
    }

    public void mostrarTauller(char[][] tauler) {
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                System.out.print(tauler[i][j]);
                if (j < tauler[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < tauler.length - 1) {
                System.out.println("-----");
            }
        }
    }

    public void recollirJugada(){
        throw new UnsupportedOperationException();
    }

    public void fiDePartida(){
        throw new UnsupportedOperationException();
    }

    public void Configuració(){
        throw new UnsupportedOperationException();
    }

    public void Sortir(){
        throw new UnsupportedOperationException();
    }

    


}
