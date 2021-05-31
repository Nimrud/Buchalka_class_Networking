package m04_high_level_APIs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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

            URI baseUri = new URI("http://www.example.com");
            URI uriDatabase = new URI("/database/tables/");   // relative URI
            URI resolvedUri = baseUri.resolve(uriDatabase);       // absolute URI
            URL url1 = resolvedUri.toURL();
            System.out.println("URL: " + url1);

        } catch (URISyntaxException e) {
            System.out.println("URI bad syntax: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }

    }
}
