public class Main {
    public static void main(String[] args) {
        
        Tui tui = new Tui();
        Joc joc = new Joc();

        int opcio = tui.mostrarMenu();

        switchMenu(opcio, joc, tui);
            

}

// method for switch menu
    private static void switchMenu(int opcio, Joc joc, Tui tui) {
        switch (opcio) {
            case 1:
                joc.novaPartida();
                opcio1(joc, tui);
                break;
            case 2:
                joc.CarregarPartida();
                opcio2(joc, tui);
                break;
            case 3:
                tui.Configuracio();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            case 4:
                System.out.println("Sortint del joc...");
                System.exit(0);
                break;
            default:
                System.out.println("Opció no vàlida");
                break;
        }
    }


    private static void opcio1(Joc joc, Tui tui) {
        boolean guanyador = false;
        int[] jugada;
        while (!guanyador) {
            tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
            jugada = tui.recollirJugada();
            if(jugada[0] == -1 && jugada[1] == -1){
                joc.guardarPartida();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            }
            joc.jugar(jugada[0], jugada[1]);
            guanyador = joc.jugadaGuanyadora(jugada[0], jugada[1]);
        }
        tui.fiDePartida(guanyador ? joc.getTorn() : 0);
    }

    private static void opcio2(Joc joc, Tui tui) {
        boolean guanyador = false;
        int[] jugada;
        while (!guanyador) {
            tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
            jugada = tui.recollirJugada();
            if(jugada[0] == -1 && jugada[1] == -1){
                joc.guardarPartida();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            }
            joc.jugar(jugada[0], jugada[1]);
            guanyador = joc.jugadaGuanyadora(jugada[0], jugada[1]);
        }
        tui.fiDePartida(guanyador ? joc.getTorn() : 0);
    }








}
