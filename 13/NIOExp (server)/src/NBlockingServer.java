import java.io.IOException;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;

public class NBlockingServer {

    final static String ADDRESS = "localhost";
    final static int PORT = 9090;

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    private NBlockingServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

//            serverSocketChannel.bind(new InetSocketAddress(ADDRESS, PORT));
            serverSocketChannel.socket().bind(new InetSocketAddress(ADDRESS, PORT));
//            selector = Selector.open();
            selector = SelectorProvider.provider().openSelector();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startServer() throws IOException {
        HashMap<SocketChannel, Connectons> connections = new HashMap<>();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.println("Waiting selector.select()");
            selector.select();

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {

                SelectionKey key = keys.next();
                keys.remove();
                System.out.println("keyOops" + key.interestOps());


                if (!key.isValid()) continue;
                if (key.isAcceptable()) {
                    System.out.println("Enter to acceptBlock");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    connections.put(socketChannel, new Connectons(socketChannel));
                    socketChannel.write(ByteBuffer.wrap("Enter the name".getBytes()));

                }
                if (key.isReadable()) {
                    System.out.println("Enter to Read block");
                    buffer.clear();
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    socketChannel.read(buffer);
                    String text = readBuffer(buffer);

                    Connectons current = connections.get(socketChannel);
                    if (current.getUsernName()==null) {
                        current.setUsernName(text);
                        System.err.println("User " + text + " connected.");
                        continue;
                    }
                    text = current.usernName + ": " + text;
                    System.err.println(text);

                    for (SocketChannel ch : connections.keySet()) {
                        if (ch == socketChannel) continue;
                        ch.write(ByteBuffer.wrap(text.getBytes()));
                    }
//                    socketChannel.write(ByteBuffer.wrap(text.getBytes()));
//                    key.interestOps(SelectionKey.OP_READ);        ключь уже в режиме read.



                }


            }
        }
    }

    String readBuffer (ByteBuffer buffer) {
        StringBuilder builder = new StringBuilder();
        int pos = buffer.position();
        buffer.flip();
        for (int i=0; i<pos; i++) {
            builder.append( (char)buffer.get());
        }
        return builder.toString();
    }

    class Connectons {

        private SocketChannel channel;
        private String usernName;

        Connectons (SocketChannel channel) {
            this.channel = channel;
        }


        public SocketChannel getChannel() {
            return channel;
        }

        void setUsernName(String name) {
            usernName = name;
        }

        public String getUsernName() {
            return usernName;
        }
    }

    public static void main(String[] args) throws IOException {
        new NBlockingServer().startServer();
    }
}
