package m03_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDP_client {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            // getLocalHost() - ponieważ uruchamiamy serwer na naszym własnym kompie
            // getByName() - jeśli serwer jest na innej maszynie
            DatagramSocket datagramSocket = new DatagramSocket();
            // datagramSocket nie jest przypisany do żadnego portu

            Scanner sc = new Scanner(System.in);
            String input;
            do {
                System.out.print("Please enter a message: ");
                input = sc.nextLine();
                System.out.println("Sending message: " + input);

                byte[] buffer = input.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                // adres, na który wysyłamy (hosta), oraz port - te informacje znajdują się w każdym wysłanym pakiecie
                datagramSocket.send(packet);

                // odbieranie wiadomości zwrotnej z serwera (zazwyczaj się tego nie robi w UDP!):
                byte[] buffer2 = new byte[50];
                packet = new DatagramPacket(buffer2, buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Text received: " + new String(buffer2, 0, packet.getLength()));

            } while (!input.equals("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
