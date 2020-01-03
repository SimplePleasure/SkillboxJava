import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.*;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class NIOServerTest {

    public static void main(String[] args) throws Exception {


        AsynchronousServerSocketChannel channel =
                AsynchronousServerSocketChannel.open().bind(null);


////================== Подход Future ==========================================
//        Future <AsynchronousSocketChannel> future = channel.accept();
//        future.cancel(true);
//        System.err.println(future.isDone());
//        System.err.println(future.isCancelled());


//==================== CompletionHandler подход ===============================



        Integer num = 0;
        channel.accept(
                num, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                    public void completed(AsynchronousSocketChannel client, Object attachment) {
                        //do something
                    }
                    public void failed(Throwable exc, Object attachment) {
                        //do something
                    }
                });

    }
}
