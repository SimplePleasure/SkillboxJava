import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ProduceConsume {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService ex = Executors.newFixedThreadPool(2);
        final Processor processor = new Processor();
        ex.execute(processor::generate);
        ex.execute(processor::writeNums);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
    }

    public static class Processor {

        long start;
        final String POSION_PILL = "stop";
        LinkedBlockingDeque<StringBuilder> deque;

        void generate() {

            start= System.currentTimeMillis();
            deque  = new LinkedBlockingDeque<>();
            GenericOrder generate = new GenericOrder(deque, POSION_PILL);
            generate.generate();
        }

        void writeNums() {
            try (FileOutputStream fos = new FileOutputStream("res/test.txt");
                                             PrintWriter pw = new PrintWriter(fos)) {

                while (true) {
                    StringBuilder buf = deque.take();
                    if (buf.toString().equals(POSION_PILL)) break;
                    pw.write(buf.toString());
                }

                fos.getChannel().force(true);
                System.out.println("Writing completed");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}



