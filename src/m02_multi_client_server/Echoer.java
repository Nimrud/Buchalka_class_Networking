package m02_multi_client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                System.out.println("Received client (" + Thread.currentThread().getName() + ") input: " + echoString);
                if (echoString.equals("exit")) {
                    break;
                }
                output.println("Server response: " + echoString);
            }
        } catch (IOException e) {
            System.out.println();
        } finally {
            try {
                socket.close();
                System.out.println("Connection in " + Thread.currentThread().getName() + " closed");
            } catch (IOException e) {
                System.out.println("Couldn't close the socket: " + e.getMessage());
            }
        }
    }
}
