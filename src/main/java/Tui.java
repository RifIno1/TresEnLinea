import java.util.Scanner;

public class Tui {

    private final Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("1. Nova Partida");
        System.out.println("2. Carregar Partida");
        System.out.println("3. Configuració");
        System.out.println("4. Sortir");
        System.out.print("Escull una opció: ");
        return sc.nextInt();
    }

    public void mostrarTauller(char[][] taulell, int torn) {
        System.out.println("Jugador" + torn + " : " + (torn == 1 ? "X" : "O")); 
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell[i].length; j++) {
                System.out.print(taulell[i][j]);
                if (j < taulell[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < taulell.length - 1) {
                System.out.println("-----");
            }
        }
    }

    public int[] recollirJugada(){
        System.out.print("Introdueix la fila: ");
        while (!sc.hasNextInt()) {
            System.out.println("Introdueix un número entre 0 i 2");
            sc.next();
        }
        int fila = sc.nextInt();
        while (fila < 0 || fila > 2) {
            System.out.println("Introdueix un número entre 0 i 2");
            fila = sc.nextInt();
        }
        System.out.print("Introdueix la columna: ");
        while (!sc.hasNextInt()) {
            System.out.println("Introdueix un número entre 0 i 2");
            sc.next();
        }
        int columna = sc.nextInt();
        while (columna < 0 || columna > 2) {
            System.out.println("Introdueix un número entre 0 i 2");
            columna = sc.nextInt();
        }
        return new int[]{fila, columna};


        

        
    }

    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("Empat!");
        } else {
            if (guanyador == 1) {
                guanyador = 2;
            } else {
                guanyador = 1;
            }
            System.out.println("Ha guanyat el jugador " + guanyador);
        }
    }

    public void Configuracio(){
        throw new UnsupportedOperationException();
    }

    public void Sortir(){
        throw new UnsupportedOperationException();
    }

    


}
