package m01_basic_client_server_apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// klasy ClientApp i ServerApp uruchamiamy w oddzielnych projektach IntelliJ IDEA
// avy zasymulować działanie klienta i servera
// [tu w jednym pakiecie, w celu umieszczenia w repo GitHub]
public class ServerApp {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // częstą praktyką jest obudowanie InputStreama za pomocą BufferedReadera
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // tak samo obudowuje się OutputStream - PrintWriterem
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            // w powyższej metodzie, jeśli nie ustawimy flush na 'true', to po każdym zapisie do streama
            // trzeba byłoby użyć metody .flush(), aby wysłać dane

            // server musi być aktywny do czasu, gdy klient nie będzie go potrzebował
            // w tym celu używamy nieskończonej pętli:
            while (true) {
                String echoString = input.readLine();
                if (echoString.equals("exit")) {
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
        } catch(IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
