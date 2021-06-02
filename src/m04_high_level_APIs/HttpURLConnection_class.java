package m04_high_level_APIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnection_class {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.org");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");  // nie trzeba tego stosować, bo "GET" jest domyślnym typem
            // każda instancja HttpURLConnection może zostać użyta tylko do 1 żądania
            connection.setRequestProperty("User-Agent", "Firefox");
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode != 200) {
                System.out.println("Error reading web page");
                return;
            }

            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }
}
