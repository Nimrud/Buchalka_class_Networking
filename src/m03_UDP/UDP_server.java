package m03_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// UDP - User Datagram Protocol
// w tym wypadku host (np. serwer) domyślnie nie wysyła wiadomości zwrotnej do klienta
// używany, gdy szybkość jest ważniejsza niż to, czy każdy pakiet dotrze do klienta
// np. streaming wideo lub aplikacje typu Skype
// W normalnej aplikacji trzeba użyć wątków (jak w module 02)
public class UDP_server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);

            while (true) {
                // odczyt wiadomości od klienta:
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                //System.out.println("Text received: " + new String(buffer));
                System.out.println("Text received (extracted): " + new String(buffer, 0, packet.getLength()));
                // drugi sout wyświetla nie całe 50 znaków (z nullami), ale sam wpisany tekst

                // wysyłanie wiadomości zwrotnej do klienta (zazwyczaj się tego nie robi w UDP!):
                String returnString = "echo: " + new String(buffer, 0, packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
