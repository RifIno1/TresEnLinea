public class Main {
    public static void main(String[] args) {
        
        Tui tui = new Tui();
        Joc joc = new Joc();

        tui.mostrarMenu();
        joc.novaPartida();
        joc.CarregarPartida();


        tui.Configuració();
        tui.Sortir();


    }
}