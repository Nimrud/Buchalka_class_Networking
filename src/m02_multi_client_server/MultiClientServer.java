package m02_multi_client_server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiClientServer {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {
                // za każdym razem odpalamy nowy wątek przy nawiązaniu połączenia
                // (Echoer extends Thread)
                // czyli każdy Klient jest obsługiwany w osobnym wątku
                new Echoer(serverSocket.accept()).start();
//                dłuższy zapis:
//                Socket socket = serverSocket.accept();
//                Echoer echoer = new Echoer(socket);
//                echoer.start();
            }
        } catch(IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
