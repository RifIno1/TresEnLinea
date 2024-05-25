package utils;
// fix ruta relativa por todoo
import java.util.Scanner;

public class Tui {

    private final Scanner sc = new Scanner(System.in);

    // method to display the menu options and return the selected option
    public int mostrarMenu() {
        showMessage("------------ Menú ------------");
        showMessage("--    1. Nova Partida       --");
        showMessage("--    2. Carregar Partida   --");
        showMessage("--    3. Configuració       --");
        showMessage("--    4. Sortir             --");
        showMessage("------------------------------");
        showMessageInSameLine("Escull una opció : ");
        return sc.nextInt();
    }

    // method to display the board with the current player's turn (X or O)
    public void mostrarTauller(char[][] taulell, int torn) {
        showMessage("Jugador" + torn + " : " + (torn == 1 ? "X" : "O"));
        // Display the board with the current state of the game
        for (char[] chars : taulell) {
            showMessageInSameLine("|");
            for (char aChar : chars) {
                showMessageInSameLine(aChar + "|");
            }
            showMessage("");
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
        showMessage("Introdueix la fila i la columna on vols posar la fitxa (0-9)");
        showMessageInSameLine("Fila: ");
        jugada[0] = sc.nextInt();
        showMessageInSameLine("Columna: ");
        jugada[1] = sc.nextInt();
        // Return the player's move as an array of integers (row, column)
        return jugada;
    }

    // method to display the winner of the game or a draw if there is no winner
    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            showMessage("Empat!");
        } else {
            showMessage("Ha guanyat el jugador " + (guanyador == 1 ? "O" : "X"));
            showMessage("------------------------------");
        }
    }

    // method to exit the game when the user selects the exit option from the menu
    public void Sortir(){
        showMessage("Gracias por jugar. ¡Adiós!");
        System.exit(0);
    }

    // method to show messages to the terminal
    public static void showMessage(String message) {
        System.out.println(message);
    }

    // method to show messages to the terminal in the same line
    public void showMessageInSameLine(String message) {
        System.out.print(message);
    }
}
