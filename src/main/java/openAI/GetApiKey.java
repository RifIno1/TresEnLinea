package openAI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import log.LogClass;


public class GetApiKey {

    /*
    (async () => {
    const rawResponse = await fetch('https://achraf.tech/APIKEY.php', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });
    console.log(rawResponse.text());
  })();
     */

    public static String sendPostRequest() {
        String urlString = "https://achraf.tech/APIKEY.php";
        StringBuilder response = new StringBuilder();

        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                os.write(0);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

        } catch (Exception e) {
            LogClass.logException(e);
        }

        return response.toString();
    }
}