import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tui {

    private final Scanner sc = new Scanner(System.in);

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

    public void mostrarTauller(char[][] taulell, int torn) {
        System.out.println("Jugador" + torn + " : " + (torn == 1 ? "X" : "O"));
        for (int i = 0; i < taulell.length; i++) {
            System.out.print("|");
            for (int j = 0; j < taulell[i].length; j++) {
                System.out.print(taulell[i][j] + "|");
            }
            System.out.println();
            if (i < taulell.length - 1) {
            }
        }
    }

    public int[] recollirJugada() {
        int[] jugada = new int[2];
        System.out.println("Introdueix la fila i la columna on vols posar la fitxa (0-2)");
        System.out.print("Fila: ");
        jugada[0] = sc.nextInt();
        System.out.print("Columna: ");
        jugada[1] = sc.nextInt();
        return jugada;
    }

    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("Empat!");
        } else {
            System.out.println("Ha guanyat el jugador " + (guanyador == 1 ? "O" : "X"));
        }
    }

    //metodo para guardar la configuracion en un archivo
    public void Configuracio() {
        int tamanoMostrador; // Tamaño por defecto
        System.out.println("Ingrese el nuevo tamaño del mostrador (mínimo 3, máximo 10):");
        while (!sc.hasNextInt()) {
            System.out.println("No es un numero !!");
            sc.next();
        }
        tamanoMostrador = sc.nextInt();
        while (tamanoMostrador <= 3 || tamanoMostrador >= 10) {
            System.out.println("Introduzca un número entre 3 y 10");
            tamanoMostrador = sc.nextInt();
        }
        guardarConfiguracion(tamanoMostrador);


    }


    private void guardarConfiguracion(int tamanoMostrador) {
        try {
            FileWriter myWriter = new FileWriter("C://files/config.txt");
            myWriter.write(String.valueOf(tamanoMostrador));
            myWriter.close();
            System.out.println("Configuración guardada correctamente");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }




    public void Sortir(){
        throw new UnsupportedOperationException();
    }

    


}
