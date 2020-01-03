import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class NioAsync {

    public static void main(String[] args) throws Exception {

        Path path = Paths.get("./res/1.txt");
        System.out.println(Files.exists(path));
//        Path path2 = Paths.get("C:\\Users\\Костя\\Desktop\\Projects\\13\\WorkingWithXML\\res\\data-18M.xml");
//        System.out.println(Files.exists(path2));


        AsynchronousFileChannel channel = AsynchronousFileChannel.open(
                path, StandardOpenOption.READ, StandardOpenOption.WRITE);


        ByteBuffer bb = ByteBuffer.allocate(664);
        Future f = channel.read(bb, 663);
        f.get();


        byte [] res = bb.array();
        for (int i = 0; i<res.length; i++) {
            System.out.print((char)res[i]);
        }

        /*
        Если в байтбуффер класть элементы методом put и превысить размер allocate - получаем
        java.nio.BufferOverflowException
        Получим ли мы Exception если заполнять буффер  AsynchronousFileChannel.read() ?
         */









    }
}
