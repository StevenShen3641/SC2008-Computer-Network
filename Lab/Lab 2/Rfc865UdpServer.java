import java.net.*;
import java.io.*;

public class Rfc865UdpServer {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        final int PORT = 17;
        final String REPLY = "Message received!";
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            System.err.println(e);
            System.exit(-1);
        }
        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                byte[] requestData = new byte[512];
                DatagramPacket request = new DatagramPacket(requestData, requestData.length);
                socket.receive(request);
                System.out.println("Received: " + new String(requestData));
                //
                // 3. Send UDP reply to client
                //
                DatagramPacket reply = new DatagramPacket(REPLY.getBytes(), REPLY.length(), request.getSocketAddress());
                socket.send(reply);
                System.out.println("Replied: " + REPLY);
            } catch (IOException e) {
                System.err.println(e);
                System.exit(-1);
            }
        }
    }
}