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
        int mida = 3;
        try {
            // Create a Scanner object to read the contents of the config.txt file
            Scanner reader = new Scanner(new File("C://files/config.txt"));
            // Read the size of the board from the first line of the file and convert it to an integer value
            mida = reader.nextInt();
            // Close the Scanner object after reading the file
            reader.close();
        } catch (IOException e) {
            // Display an error message if the config.txt file is not found or cannot be read
            System.out.println("No se puede leer el archivo de configuración");
        }
        // Create a new 3x3 board with empty cells represented by space characters ' '
        taulell = new char[mida][mida];
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

            // Read the size of the board from the second line of the file and convert it to an integer value
            int size = reader.nextInt();
            reader.nextLine();

            // Read the contents of the taulell matrix from the rest of the lines in the file and convert them to a 2D char array
            taulell = new char[size][size];
            for (int i = 0; i < size; i++) {
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
    char jugador = taulell[fila][columna];
    int count = 0;

    // check consecutive cells horizontally
    for (int i = 0; i < taulell.length; i++) {
        if (taulell[fila][i] == jugador) {
            count++;
        } else {
            count = 0;
        }

        if (count == 3) {
            return true;
        }
    }

    count = 0;

    // check consecutive cells vertically
        for (char[] chars : taulell) {
            if (chars[columna] == jugador) count++;
            else count = 0;

            if (count == 3) return true;
        }

    count = 0;

    // check cells diagonally 1     
    // calcul from where row should start
    int row = fila - Math.min(fila, columna);
    // calcul from where col should start
    int col = columna - Math.min(fila, columna);
    // check cells diagonally 1
    while (row < taulell.length && col < taulell.length) {
        if (taulell[row][col] == jugador) {
            count++;
        } else {
            count = 0;
        }

        if (count == 3) return true;
        
        // increment row and col
        row++;
        col++;
    }


    count = 0; // empieza de 0
    // check cells diagonally 2 
    row = fila - Math.min(fila, taulell.length - 1 - columna);
    col = columna + Math.min(fila, taulell.length - 1 - columna);
    while (row < taulell.length && col >= 0) {
        if (taulell[row][col] == jugador) {
            count++;
        } else {
            count = 0;
        }

        if (count == 3) {
            return true;
        }
        // increment row and decrement col
        row++;
        col--;
    }
    // si no hay 3 in count returna false
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
            FileWriter writer = getFileWriter(savedGameFile);

            writer.close();
            // Display a message indicating that the current game has been saved successfully to the file path
            System.out.println("Partida guardada correctamente en " + savedGameFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("no guardada");
        }
    }

    private FileWriter getFileWriter(File savedGameFile) throws IOException {
        FileWriter writer = new FileWriter(savedGameFile);
        writer.write(Integer.toString(torn));
        // Write a new line character to separate the player's turn from the taulell matrix
        writer.write(System.lineSeparator());

        // Write the size of the board to the second line of the file
        writer.write(Integer.toString(taulell.length));
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
        return writer;
    }


    // “minimax” nou mètode recursiu que rep un taulell, i que retorna un array d’enters que representa la casella on la IA hauria de jugar per intentar guanyar
    // public int[] minimax() {





}
