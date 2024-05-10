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

    public void novaPartida()
    {
        taulell = new char[3][3];
        for (char[] chars : taulell) {
            Arrays.fill(chars, ' ');
        }
        torn = 1;
    }

    public void CarregarPartida() {
        // Load the saved game file
        File savedGamesFolder = new File("C://files/savedgames");
        File[] savedGameFiles = savedGamesFolder.listFiles();
        if (savedGameFiles == null || savedGameFiles.length == 0) {
            System.out.println("No hi ha cap partida guardada");
            return;
        }

        // Display the list of saved game files
        System.out.println("Partides guardades:");
        for (int i = 0; i < savedGameFiles.length; i++) {
            System.out.println((i + 1) + ". " + savedGameFiles[i].getName());
        }

        // Ask the user to choose a saved game file
        System.out.print("Escull una partida guardada: ");
        int savedGameIndex = new Scanner(System.in).nextInt() - 1;
        if (savedGameIndex < 0 || savedGameIndex >= savedGameFiles.length) {
            System.out.println("Opció incorrecta");
            return;
        }

        // Load the saved game file
        File savedGameFile = savedGameFiles[savedGameIndex];
        try {
            Scanner reader = new Scanner(savedGameFile);

            // Read the current player's turn from the first line of the file
            torn = reader.nextInt();
            reader.nextLine();

            // Read the contents of the taulell matrix from the rest of the lines
            taulell = new char[3][3];
            for (int i = 0; i < 3; i++) {
                taulell[i] = reader.nextLine().toCharArray();
            }

            

            reader.close();
            System.out.println("Partida carregada correctament de " + savedGameFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            return taulell[0][2] == jugador && taulell[1][1] == jugador && taulell[2][0] == jugador;
        }
        return false;
    }

    public void guardarPartida() {
        try {
            // Create the "savedgames" folder if it doesn't exist
            File savedGamesFolder = new File("C://files/savedgames");
            if (!savedGamesFolder.exists()) {
                savedGamesFolder.mkdir();
            }

            // Create a file with the current date and time as the name
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = dateFormat.format(new Date()) + ".txt";
            File savedGameFile = new File(savedGamesFolder, fileName);

            // Write the current player's turn to the first line of the file
            FileWriter writer = new FileWriter(savedGameFile);
            writer.write(Integer.toString(torn));
            writer.write(System.lineSeparator());

            // Write the contents of the taulell matrix to the rest of the lines
            for (char[] row : taulell) {
                for (char cell : row) {
                    writer.write(cell);
                }
                writer.write(System.lineSeparator());
            }

            writer.close();
            System.out.println("Partida guardada correctamente en " + savedGameFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
