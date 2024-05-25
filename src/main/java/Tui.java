// fix ruta relativa por todoo

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tui {

    private final Scanner sc = new Scanner(System.in);

    // method to display the menu options and return the selected option
    public int mostrarMenu() {
        System.out.println("------------ Menú ------------");
        System.out.println("--    1. Nova Partida       --");
        System.out.println("--    2. Carregar Partida   --");
        System.out.println("--    3. Configuració       --");
        System.out.println("--    4. Sortir             --");
        System.out.println("------------------------------");
        System.out.print("Escull una opció : ");
        return sc.nextInt();
    }

    // method to display the board with the current player's turn (X or O)
    public void mostrarTauller(char[][] taulell, int torn) {
        System.out.println("Jugador" + torn + " : " + (torn == 1 ? "X" : "O"));
        // Display the board with the current state of the game
        for (char[] chars : taulell) {
            System.out.print("|");
            for (char aChar : chars) {
                System.out.print(aChar + "|");
            }
            System.out.println();
        }
    }

    // method to return the board as a string to be used in the chatGPT method
    public String taulellToString(char[][] taulell) {
        StringBuilder taulellString = new StringBuilder();
        for (int i = 0; i < taulell.length; i++) {
            taulellString.append("[");
            for (int j = 0; j < taulell[i].length; j++) {
                taulellString.append(taulell[i][j]);
                if (j < taulell[i].length - 1) {
                    taulellString.append(",");
                }
            }
            taulellString.append("]");
            if (i < taulell.length - 1) {
                taulellString.append(",");
            }
        }
        return taulellString.toString();
    }

    // method to get the player's move (row and column) from the console
    public int[] recollirJugada() {
        int[] jugada = new int[2];
        System.out.println("Introdueix la fila i la columna on vols posar la fitxa (0-9)");
        System.out.print("Fila: ");
        jugada[0] = sc.nextInt();
        System.out.print("Columna: ");
        jugada[1] = sc.nextInt();
        // Return the player's move as an array of integers (row, column)
        return jugada;
    }

    // method to display the winner of the game or a draw if there is no winner
    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("Empat!");
        } else {
            System.out.println("Ha guanyat el jugador " + (guanyador == 1 ? "O" : "X"));
            System.out.println("------------------------------");
        }
    }


    // method to exit the game when the user selects the exit option from the menu
    public void Sortir(){
        System.out.println("Gracias por jugar. ¡Adiós!");
        System.exit(0);
    }

    


}
