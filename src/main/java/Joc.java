import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Joc {

    private char [][] taulell;
    private int torn;

    public char[][] getTaulell() {
        return taulell;
    }
    public int getTorn() {
        return torn;
    }

    // method to start a new game and initialize the board
    public void novaPartida()
    {
        // Create a new 3x3 board with empty cells represented by space characters ' '
        taulell = new char[3][3];
        for (char[] chars : taulell) {
            Arrays.fill(chars, ' ');
        }
        // Set the first player's turn to 1 (player X) at the beginning of the game
        torn = 1;
    }


    // method to load a saved game from a file and resume playing from the saved state
    public void CarregarPartida() {
        // Load the saved game file from the "savedgames" folder
        File savedGamesFolder = new File("C://files/savedgames");
        // Get the list of saved game files in the folder "savedgames"
        File[] savedGameFiles = savedGamesFolder.listFiles();
        // Check if there are any saved game files in the folder
        if (savedGameFiles == null || savedGameFiles.length == 0) {
            System.out.println("No hi ha cap partida guardada");
            return;
        }

        // Display the list of saved game files to the user with their corresponding indices (1, 2, 3, ...)
        System.out.println("Partides guardades:");
        for (int i = 0; i < savedGameFiles.length; i++) {
            System.out.println((i + 1) + ". " + savedGameFiles[i].getName());
        }

        // Ask the user to choose a saved game
        System.out.print("Escull una partida guardada: ");
        // Subtract 1 from the user's input to get the index of the selected saved game
        int savedGameIndex = new Scanner(System.in).nextInt() - 1;
        // Check if the selected index is valid (within the range of the array) before loading the saved game file
        if (savedGameIndex < 0 || savedGameIndex >= savedGameFiles.length) {
            System.out.println("Opció incorrecta");
            return;
        }

        // Load the saved game file based on the selected index from the list of saved game files
        File savedGameFile = savedGameFiles[savedGameIndex];
        try {
            // Read the contents of the saved game file using a Scanner object
            Scanner reader = new Scanner(savedGameFile);

            // Read the current player's turn from the first line of the file and convert it to an integer value
            torn = reader.nextInt();
            reader.nextLine();

            // Read the contents of the taulell matrix from the rest of the lines in the file and convert them to a 2D char array
            taulell = new char[3][3];
            for (int i = 0; i < 3; i++) {
                taulell[i] = reader.nextLine().toCharArray();
            }

            

            reader.close();
            // Display a message indicating that the saved game has been loaded successfully from the file path
            System.out.println("Partida carregada correctament de " + savedGameFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("no puede carregar");
        }
    }
    
    // method to play a move on the board based on the current player's turn and the selected cell (row, column) by the player
    public void jugar(int fila, int columna) {
        // Check if the cell is empty before making a move in that cell to avoid overwriting existing moves by the players
        if (taulell[fila][columna] == ' ') {
            // Set the cell to 'X' or 'O' based on the current player's turn (1 for 'X', 2 for 'O') and switch the turn to the other player after the move is made 
            if (torn == 1) {
                taulell[fila][columna] = 'X';
                torn = 2;
            } else {
                taulell[fila][columna] = 'O';
                torn = 1;
            }
        }
    }

    // method to check if the current move by the player results in a winning combination on the board
    public boolean jugadaGuanyadora(int fila, int columna) {
        // Get the player's symbol ('X' or 'O') based on the current player's turn
        char jugador = taulell[fila][columna];

        // Check if the player has a winning combination in any row on the board after the current move
        if (taulell[fila][0] == jugador && taulell[fila][1] == jugador && taulell[fila][2] == jugador) {
            return true;
        }
        // Check if the player has a winning combination in any column on the board after the current move
        if (taulell[0][columna] == jugador && taulell[1][columna] == jugador && taulell[2][columna] == jugador) {
            return true;
        }
        // Check if the player has a winning combination in the main diagonal (top-left to bottom-right) on the board after the current move
        if (fila == columna) {
            if (taulell[0][0] == jugador && taulell[1][1] == jugador && taulell[2][2] == jugador) {
                return true;
            }
        }
        // Check if the player has a winning combination in the secondary diagonal (top-right to bottom-left) on the board after the current move
        if (fila + columna == 2) {
            return taulell[0][2] == jugador && taulell[1][1] == jugador && taulell[2][0] == jugador;
        }
        // Return false if there is no winning combination for the player after the current move
        return false;
    }

    public void guardarPartida() {
        try {
            // Create the "savedgames" folder if it doesn't exist
            File savedGamesFolder = new File("C://files/savedgames");
            // Check if the folder exists, and create it if it doesn't
            if (!savedGamesFolder.exists()) {
                // Create the directory for the saved games
                savedGamesFolder.mkdir();
            }

            // Create a file with the current date and time as the name
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = dateFormat.format(new Date()) + ".txt";
            File savedGameFile = new File(savedGamesFolder, fileName);

            // Write the current player's turn to the first line of the file
            FileWriter writer = new FileWriter(savedGameFile);
            writer.write(Integer.toString(torn));
            // Write a new line character to separate the player's turn from the taulell matrix
            writer.write(System.lineSeparator());

            // Write the contents of the taulell matrix to the rest of the lines
            for (char[] row : taulell) {
                for (char cell : row) {
                    // Write each cell of the taulell matrix to the file 
                    writer.write(cell);
                }
                // Write a new line character to separate each row of the taulell matrix
                writer.write(System.lineSeparator());
            }

            writer.close();
            // Display a message indicating that the current game has been saved successfully to the file path
            System.out.println("Partida guardada correctamente en " + savedGameFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
