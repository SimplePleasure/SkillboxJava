import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/*
example from:
https://www.tutorialspoint.com/java_nio/java_nio_server_socket_channel.htm
 */


public class NIOExample {

        public static class SocketChannelClient {
            public static void main(String[] args) throws IOException {

                ServerSocketChannel serverSocket = null;
                SocketChannel client = null;
                serverSocket = ServerSocketChannel.open();
                serverSocket.socket().bind(new InetSocketAddress(9000));
                client = serverSocket.accept();
                System.out.println("Connection Set:  " + client.getRemoteAddress());
                Path path = Paths.get("./res/1.txt");
                FileChannel fileChannel = FileChannel.open(path,
                        EnumSet.of(StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING,
                                StandardOpenOption.WRITE)
                );
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while(client.read(buffer) > 0) {
                    buffer.flip();
                    fileChannel.write(buffer);
                    buffer.clear();
                }
                fileChannel.close();
                System.out.println("File Received");
                client.close();
            }
        }


        public static class SocketChannelServer {
            public static void main(String[] args) throws IOException {
                SocketChannel server = SocketChannel.open();
                SocketAddress socketAddr = new InetSocketAddress("localhost", 9000);
                server.connect(socketAddr);
                Path path = Paths.get("./res/1.txt");
                FileChannel fileChannel = FileChannel.open(path);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while(fileChannel.read(buffer) > 0) {
                    buffer.flip();
                    server.write(buffer);
                    buffer.clear();
                }
                fileChannel.close();
                System.out.println("File Sent");
                server.close();
            }
        }
}
