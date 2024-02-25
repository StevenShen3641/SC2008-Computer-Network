/**
 * Name: Shen Chihao
 * Group: YourLabGroup
 * IP Address: YourClientIPAddress
 */

import java.net.*;
import java.io.IOException;

public class Rfc865UdpClient {

    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //
        final int PORT = 17;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println(e);
            System.exit(-1);
        }

        try {
            //
            // 2. Send UDP request to server
            //
            byte[] message;
//            InetAddress address = InetAddress.getByName("127.0.0.1");
            InetAddress address = InetAddress.getByName("172.20.186.160");
//            InetAddress address = InetAddress.getByName("hwlab1.scse.ntu.edu.sg");
            message = String.format("Shen Chihao, ***, %s", InetAddress.getLocalHost().getHostAddress()).getBytes();

            DatagramPacket request = new DatagramPacket(message, message.length);
            socket.connect(address, PORT);
            System.out.println("Sending: " + new String(message));
            socket.send(request);
            //
            // 3. Receive UDP reply from server
            //
            byte[] repliedMessage = new byte[512];
            DatagramPacket reply = new DatagramPacket(repliedMessage, repliedMessage.length);
            socket.receive(reply);
            System.out.println("Received: " + new String(repliedMessage));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
}