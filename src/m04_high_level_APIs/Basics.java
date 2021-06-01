package m04_high_level_APIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Basics {
    public static void main(String[] args) {
        try {
            // schemat URI:
            // URI = scheme:[//authority]path[?query][#fragment]
            // authority = [userinfo@]host[:port]
            URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");

            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Scheme-specific part: " + uri.getSchemeSpecificPart());
            System.out.println("Authority: " + uri.getAuthority());
            System.out.println("User info: " + uri.getUserInfo());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());

            //URL url = uri.toURL();
            //System.out.println("URL: " + url);


            // Łączenie URI - resolve():
            URI baseUri = new URI("http://www.example.com");
            URI uriDatabase = new URI("/database/tables/");   // relative URI
            URI resolvedUri = baseUri.resolve(uriDatabase);       // absolute URI
            URL url1 = resolvedUri.toURL();
            System.out.println("URL: " + url1);


            // Dzielenie URI - relativize():
            URI relativizedUri = baseUri.relativize(resolvedUri);
            System.out.println("Relativized URI: " + relativizedUri);


            // Czytanie zawartości stron www (metoda 1 - stream):
            URL url = new URL("http://example.org");
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line = "";
            while (line != null) {
                line = inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();


            // Czytanie zawartości stron www (metoda 2 - klasa URLConnection):
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();     // pomiędzy .openConnection() a .connect() może być kilka metod dot. konfiguracji połączenia

            BufferedReader inputStream2 = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line2 = "";
            while (line2 != null) {
                line2 = inputStream2.readLine();
                System.out.println(line2);
            }
            inputStream2.close();

        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("URI bad syntax: " + e.getMessage());
        }

    }
}
