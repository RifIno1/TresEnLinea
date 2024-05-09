import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("-------------");
        for (int i = 0; i < taulell.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < taulell[i].length; j++) {
                System.out.print(taulell[i][j] + " | ");

            }
            System.out.println();
            if (i < taulell.length - 1) {
            }
            System.out.println("-------------");
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

    //metodo para guardar la configuracion en un archivo
    public int tamanoMostrador;   // variable para almacenar el tamaño del mostrador
    public void Configuracio() {
        System.out.println("Ingrese el nuevo tamaño del mostrador (mínimo 3, máximo 10):");
        Scanner sc = new Scanner(System.in);
        int nuevoTamano = sc.nextInt();
        if (nuevoTamano >= 3 && nuevoTamano <= 10) {
            tamanoMostrador = nuevoTamano; // Actualizar el tamaño del mostrador
            guardarConfiguracion(tamanoMostrador); // Guardar la configuración en un archivo
            System.out.println("Configuración guardada correctamente.");

        } else {
            System.out.println("El tamaño del mostrador debe estar entre 3 y 10.");
        }
    }


        private void guardarConfiguracion(int tamanoMostrador){
        try {
            FileWriter writer = new FileWriter("C://files/config.txt");
            writer.write(tamanoMostrador);

        } catch (IOException e) {
            System.out.println("Error al guardar la configuracion en el archivo.");
            e.printStackTrace();
        }

        }




    public void Sortir(){
        throw new UnsupportedOperationException();
    }

    


}
