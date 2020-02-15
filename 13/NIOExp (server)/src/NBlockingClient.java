import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class NBlockingClient {


    public void selectorListener() throws IOException {

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        channel.connect(new InetSocketAddress("localhost", 9090));


        ExecutorService ex = Executors.newFixedThreadPool(1);            //======   Поток слушает сообщения
        BlockingDeque<String> queue = new LinkedBlockingDeque<>(2);       //======   на отправку.
        ex.execute(() -> {                                                         //======
            try (Scanner scanner = new Scanner(System.in)) {                       //======
                while (true) {                                                     //======
                    queue.put(scanner.nextLine());                                 //======
                    SelectionKey key = channel.keyFor(selector);                   //======
                    key.interestOps(SelectionKey.OP_WRITE);                        //======
                    selector.wakeup();                                             //======
                    System.out.println("singleTh: key set to" + key.interestOps());
                    System.out.println(queue.size());                              //======
                }                                                                  //======
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                //======
        });                                                                        //======


        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.println("Waiting selector.select()");
            selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {

                SelectionKey key = it.next();
                it.remove();
                System.out.println("keyOops" + key.interestOps());
                System.out.println(key.channel().isOpen());


                if (key.isConnectable()) {
                    channel.finishConnect();
                    System.out.println("Success connected: " + channel.isConnected());
                    key.interestOps(SelectionKey.OP_READ);
                    System.out.println("isConnectable2: key set to" + key.interestOps());

                } else if (key.isWritable()) {
                    try {
                        String str = queue.takeFirst();
                        channel.write(ByteBuffer.wrap(str.getBytes()));

                        key.interestOps(SelectionKey.OP_READ);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Writable. oops: " + key.interestOps());


                } else if (key.isReadable()) {
                    buffer.clear();
                    channel.read(buffer);
                    int pos = buffer.position();
                    buffer.flip();
                    String text = "";
                    for (int i = 0; i < pos; i++) {
                        text += (char) buffer.get();
                    }
                    System.out.println(text);
                    key.interestOps(SelectionKey.OP_WRITE);
                }


            }


        }
    }

    public static void main(String[] args) throws IOException {
        new NBlockingClient().selectorListener();

    }


}
