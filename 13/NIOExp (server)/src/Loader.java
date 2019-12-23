import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.*;

public class Loader {

    public static void main(String[] args) throws IOException {
//        SocketChannel channel = SocketChannel.open();
//        channel.configureBlocking(false);

//        Selector selector = Selector.open();
//        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

//        InetAddress address = InetAddress.getByName("localhost");
//        Socket soket = new Socket(address, 7077);

        ServerSocket socket = new ServerSocket(20000);
        System.out.println("waiting");
        Socket s = socket.accept();

        s.getInputStream();
        s.getOutputStream();

    }
}
