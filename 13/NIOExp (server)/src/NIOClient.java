import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NIOClient {

    static final int PORT = 9090;
    static final String ADDRESS = "localhost";
    private ByteBuffer buffer = ByteBuffer.allocate(16);

    public void run() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector = Selector.open();

        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(ADDRESS, PORT));
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.equals("q")) System.exit(0);
                try {
                    queue.put(str);
                    System.out.println(queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SelectionKey key = channel.keyFor(selector);
                key.interestOps(SelectionKey.OP_WRITE);
                selector.wakeup();
            }
        }).start();

        while (true) {
            selector.select();
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                if (selectionKey.isConnectable()) {
                    channel.finishConnect();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                } else if (selectionKey.isReadable()) {
                    buffer.clear();
                    channel.read(buffer);
                    System.out.println("Recived= " + new String(buffer.array()));
                } else if (selectionKey.isWritable()) {
                    String str = queue.poll();
                    if (str != null) {
                        channel.write(ByteBuffer.wrap(str.getBytes()));
                    }
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }



    public static void main(String[] args) {
        try {
            new NIOClient().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


