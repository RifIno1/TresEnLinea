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

    // method to display the configuration option and get the new size of the board from the console
    public void Configuracio() {
        int tamanoMostrador; 
        System.out.println("Ingrese el nuevo tamaño del mostrador (mínimo 3, máximo 10):");
        // Check if the input is an integer before reading the value
        while (!sc.hasNextInt()) {
            System.out.println("No es un numero !!");
            sc.next();
        }
        // Read the new size of the board from the console
        tamanoMostrador = sc.nextInt();
        // Validate the input to ensure it is within the specified range (3-10)
        while (tamanoMostrador < 3 || tamanoMostrador > 10) {
            System.out.println("Introduzca un número entre 3 y 10");
            tamanoMostrador = sc.nextInt();
        }

        // next boolean
        System.out.println("Quieres jugar con AI? (true/false)");
        boolean ai = sc.nextBoolean();


        // Save the new size of the board to the configuration file
        guardarConfiguracion(tamanoMostrador, ai);


    }

    // method to save the new size of the board to the configuration file
    private void guardarConfiguracion(int tamanoMostrador, boolean ai) {
        try {
            // Write the new size of the board to the configuration file
            FileWriter myWriter = new FileWriter("C://files/config.txt");
            myWriter.write(String.valueOf(tamanoMostrador));
            myWriter.write("\n");
            myWriter.write(String.valueOf(ai));
            myWriter.close();
            System.out.println("Configuración guardada correctamente");
        } catch (IOException e) {
            System.out.println("An error occurred for saving the game.");


        }
    }

    // method to exit the game when the user selects the exit option from the menu
    public void Sortir(){
        System.out.println("Sortint del joc...");
        System.exit(0);
    }

    


}
