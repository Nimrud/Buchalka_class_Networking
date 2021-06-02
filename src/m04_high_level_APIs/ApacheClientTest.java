package m04_high_level_APIs;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Apache HttpClient library: http://hc.apache.org/downloads.cgi (binary)
// plus SLF4J Simple Binding: https://mvnrepository.com/artifact/org.slf4j/slf4j-simple/1.7.30
// File -> Project structure -> Libraries -> etc.

public class ApacheClientTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org");
        request.addHeader("User-Agent", "Firefox");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            System.out.println("Response code: " + response.getCode());

            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line;

            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
