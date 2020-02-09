import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NioAsyncFileChannel {

    public static void main(String[] args) throws Exception {

        Path path = Paths.get("./res/1.txt");
        String s = "\nTest Completed";


        FieldEditCompletionHandlerApproach.read(path, 0);
        FieldEditCompletionHandlerApproach.write(path, FileEditFutureApproach.fileLength(path),
                                    "\nThis text will be writed from CompletionHandler Approach");
//        FileEditFutureApproach.printFileContent(path);
//        FileEditFutureApproach.write(path, FileEditFutureApproach.fileLength(path), s);
//        FileEditFutureApproach.printFileContent(path);
    }


    static class FileEditFutureApproach {
//        Блокирующий подход. Класс будет работать правильно при размере файла менее 65_536.
        public static int fileLength(Path path) {
            return (int)path.toFile().length();
        }

        public static void printFileContent(Path path) throws Exception {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer bb = ByteBuffer.allocate((int) channel.size());
            Future f = channel.read(bb, 0);
            f.get();
            channel.close();

            System.out.println(new String(bb.array()));
        }

        public static void write(Path path,int pos, String text) throws Exception {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            Future<Integer> f = channel.write(ByteBuffer.wrap(text.getBytes()), pos);
            System.out.println(f.get());

        }
    }

    static class FieldEditCompletionHandlerApproach {
//        Non-blocking approach.

        public static void read(Path path, int pos) throws Exception{
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer, 0, Result.res, new CompletionHandler<Integer, Boolean>() {
                @Override
                public void completed(Integer result, Boolean attachment) {
                    Result.res = true;
                }

                @Override
                public void failed(Throwable exc, Boolean attachment) {

                }
            });
            while (!Result.res) {}
            System.out.println(new String(buffer.array()));
        }

        public static void write(Path path, int pos, String text) throws Exception {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

            channel.write(ByteBuffer.wrap(text.getBytes()), pos, 0, new CompletionHandler<Integer, Integer>() {
                @Override
                public void completed(Integer result, Integer attachment) {
                    System.out.println("Completed writing.");
                }

                @Override
                public void failed(Throwable exc, Integer attachment) {

                }
            });
        }

        public static class Result {
            volatile static boolean res = false;
        }
    }
}
