import openAI.OpenAiApi;
import utils.Joc;
import utils.Tui;
// quitamos method get boolean de ai
public class Main {
    public static void main(String[] args) 
    {
        Tui tui = new Tui();
        Joc joc = new Joc();
        int opcio = tui.mostrarMenu();
        switchMenu(opcio, joc, tui);
    }


// method for switch menu
    private static void switchMenu(int opcio, Joc joc, Tui tui) {
        switch (opcio) {
            case 1:
                // new game
                joc.novaPartida();
                if(joc.getSecondBoolean()){
                    play_with_ai(joc, tui);
                } else {
                    play_with_friend(joc, tui);
                }
                break;
            case 2:
                // load game and continue playing
                // check if there is a saved game to load
                joc.CarregarPartida();
                if(joc.getSecondBoolean()){
                    play_with_ai(joc, tui);
                } else {
                    play_with_friend(joc, tui);
                }
                break;
            case 3:
                // configuration to change the size of the board
                joc.Configuracio();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            case 4:
                // exit the game
                tui.Sortir();
                break;
            default:
                // invalid option message and return to the menu
                Tui.showMessage( "Opció no vàlida");
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
        }
    }

    // method for option 1 of the menu (new game) 
    private static void play_with_friend(Joc joc, Tui tui) {
        boolean guanyador = false;
        int[] jugada;
        // loop for the game until there is a winner or a draw 
        while (!guanyador) {
            // check if there are a tauller
            if(joc.getTaulell() == null){
                switchMenu(tui.mostrarMenu(), joc, tui);
            }
            // show the board and get the move
            tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
            // get the move from the player
            jugada = tui.recollirJugada();
            // if the move is -1, save the game and return to the menu
            if(jugada[0] == -1 && jugada[1] == -1){
                joc.guardarPartida();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            }
            // else play the move and check if there is a winner
            joc.jugar(jugada[0], jugada[1]);
            guanyador = joc.jugadaGuanyadora(jugada[0], jugada[1]);

        }
        // show the board and the winner
        tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
        tui.fiDePartida(guanyador ? joc.getTorn() : 0);
        switchMenu(tui.mostrarMenu(), joc, tui);
    }


    public static void play_with_ai(Joc joc, Tui tui) {
        // from API
        OpenAiApi openAiApi = new OpenAiApi();

        // for the torn X use recollirJugada and for the turn O use api.main
        boolean guanyador = false;
        int[] jugada;
        while (!guanyador) {
            tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
            if (joc.getTorn() == 1) {
                jugada = tui.recollirJugada();
            } else {
                 jugada = openAiApi.getXY(joc.getTaulell());
            }
            if (jugada[0] == -1 && jugada[1] == -1) {
                joc.guardarPartida();
                switchMenu(tui.mostrarMenu(), joc, tui);
                break;
            }
            joc.jugar(jugada[0], jugada[1]);
            guanyador = joc.jugadaGuanyadora(jugada[0], jugada[1]);
        }
        tui.mostrarTauller(joc.getTaulell(), joc.getTorn());
        tui.fiDePartida(guanyador ? joc.getTorn() : 0);
        switchMenu(tui.mostrarMenu(), joc, tui);
    }
}
