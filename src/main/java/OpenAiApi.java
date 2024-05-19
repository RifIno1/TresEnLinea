import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class OpenAiApi {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = GetApiKey.sendPostRequest();
    private static final String MODEL = "gpt-3.5-turbo";


    @SuppressWarnings("deprecation")
    public static String chatGPT(String gameInstructions) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String question = "to win you need to have 3 O in a row, column or diagonal, " +
                        "don't play in a full cell, symbol - means empty cell, " +
                        "in the response write only X,Y and don't write anything else." +
                        "X and Y is between 0 and the number of lines";
            String prompt = question + gameInstructions;

            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(body);
            }

            @SuppressWarnings("resource")
            String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));

            return extractMessageFromJSONResponse(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public static int[] getXY(char [][] taulell) {
        Tui tui = new Tui();
        String gameInstructions = tui.taulellToString(taulell);
        String XY = chatGPT(gameInstructions);

        // get X and Y from X,Y response
        int x = Character.getNumericValue(XY.charAt(0));
        int y = Character.getNumericValue(XY.charAt(2));

        // check if x and y is between 0 and the number of lines
        if (x < 0 || x >= taulell.length || y < 0 || y >= taulell.length) {
            return getXY(taulell);
        }


        // check if the cell is empty before making a move in that cell to avoid overwriting existing moves by the players
        if (taulell[x][y] != '-' && taulell[x][y] != 'X' && taulell[x][y] != 'O') {
            System.out.println("fila : " + XY.charAt(0));
            System.out.println("columna : " + XY.charAt(2));
            return new int[]{x, y};
        }else{
            return getXY(taulell);
        }



    }


}