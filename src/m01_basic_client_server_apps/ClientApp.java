package m01_basic_client_server_apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

// klasy ClientApp i ServerApp uruchamiamy w oddzielnych projektach IntelliJ IDEA
// avy zasymulować działanie klienta i servera
// [tu w jednym pakiecie, w celu umieszczenia w repo GitHub]
public class ClientApp {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 5000)) {
            // 'localhost' to adres hosta, a '5000' to numer portu
            // w przypadku uruchamiania na 1 maszynie klienta i serwera,
            // jeśli 'localhost' nie działa, należy wpisać '127.0.0.1'

            socket.setSoTimeout(5000);
            // timeout - limit czasu na wykonanie operacji
            // zapobiega blokowaniu (po jego upłynięciu można wysłać zapytanie ponownie, przerwać wykonywanie danej operacji, poinformować użytkownika itp.)
            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.print("Enter string to be echoed: ");
                echoString = sc.nextLine();

                stringToEcho.println(echoString);
                if (!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));

        } catch (SocketTimeoutException e1) {
            System.out.println("The socket timed out");
        } catch (IOException e2) {
            System.out.println("Client exception: " + e2.getMessage());
        }
    }
}
