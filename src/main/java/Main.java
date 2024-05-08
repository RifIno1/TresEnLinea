public class Main {
    public static void main(String[] args) {
        
        Tui tui = new Tui();
        Joc joc = new Joc();

        int opcio = tui.mostrarMenu();


            switch (opcio){
                case 1:
                    joc.novaPartida();
                    opcio1(joc, tui);
                    break;
                case 2:
                    joc.CarregarPartida();
                    /*
                    opcio2(joc, tui);
                     */
                    break;
                case 3:
                    tui.Configuracio();
                    break;
                case 4:
                    tui.Sortir();
                    break;
                default :
                    System.out.println("Opció incorrecta");
                    break;
            }
            

}

    private static void opcio1(Joc joc, Tui tui) {
        boolean guanyador = false;
        int[] jugada;
        while (!guanyador) {
            tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
            jugada = tui.recollirJugada();
            joc.jugar(jugada[0], jugada[1]);
            if (joc.jugadaGuanyadora(jugada[0], jugada[1])) {
                guanyador = true;
                tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
                tui.fiDePartida(joc.getTorn());
            }
        }
    }



}
