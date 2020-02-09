import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DatagramChannelEXP {

    static ExecutorService ex = Executors.newFixedThreadPool(2);
    static InetSocketAddress serverSocket = new InetSocketAddress("localhost", 9090);
    static InetSocketAddress clientSocket = new InetSocketAddress("localhost", 9091);


    public static void main(String[] args) throws InterruptedException {
        ex.execute(KnockKnockServer::receive);
        ex.execute(Client::sent);
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.SECONDS);
    }



    public static class KnockKnockServer {
        static ByteBuffer buffer;

        public static void receive() {
            try {
                buffer = ByteBuffer.allocate(30);
                DatagramChannel datagramChannel = DatagramChannel.open().bind(serverSocket);
//                datagramChannel.configureBlocking(false);

                SocketAddress address = datagramChannel.receive(buffer);
                System.out.println("Server: Connect from " + address);

                String str = "";
                int caret = buffer.position();
                buffer.flip();
                while (buffer.position() < caret) {
                    str += (char) buffer.get();

                }

                str = str.toUpperCase();
                datagramChannel.send(ByteBuffer.wrap(str.getBytes()), address);

            } catch (IOException | NullPointerException e) {
                System.exit(0);
            }


        }
    }

    public static class Client {
        static ByteBuffer buffer;

        public static void sent () {
            try {

                System.err.println("Client: Please, type some text:");
                Scanner scanner = new Scanner(System.in);
                DatagramChannel channel = DatagramChannel.open().bind(clientSocket);
                buffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
                channel.send(buffer, serverSocket);
                System.err.println("Client: Data was sent.");

                buffer.clear();
                SocketAddress serverAdress = channel.receive(buffer);
                System.err.println("Client: Waiting reply...");
                System.err.println("Client: Reply received from" + serverAdress);

                int caretPos = buffer.position();
                buffer.flip();
                while (buffer.position() < caretPos) {
                    System.out.print((char) buffer.get());
                }

            } catch (IOException ignore) {}


        }
    }
}


