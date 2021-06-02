package m04_high_level_APIs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnection_class {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.org");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                System.out.println("== Key: " + key);
                List<String> value = entry.getValue();
                for (String string : value) {
                    System.out.println("Value: " + string);
                }
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }
}
