import java.io.*;
import java.util.concurrent.*;

public class ProduceConsume {

    long start;
    volatile boolean isDone = false;
    LinkedBlockingDeque<StringBuilder> deque;


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService ex = Executors.newFixedThreadPool(2);
        final ProduceConsume produceConsume = new ProduceConsume();
        Future produce = ex.submit(produceConsume::generate);
        Future consume = ex.submit(produceConsume::writeNums);
        
        produce.get();
        produceConsume.isDone = true;
        consume.cancel(true);


        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
    }


    void generate() {

        start = System.currentTimeMillis();
        deque = new LinkedBlockingDeque<>();
        GenericOrder generate = new GenericOrder(deque);
        generate.generate();
    }

    void writeNums() {

        try (FileOutputStream fos = new FileOutputStream("res/test.txt");
             PrintWriter pw = new PrintWriter(fos)) {

            while (!isDone || deque.size() > 0) {
                try {
                    StringBuilder buf = deque.take();
                    pw.write(buf.toString());
                } catch (InterruptedException interruption) {
                    Thread.currentThread().interrupt();
                }
            }

            fos.getChannel().force(true);
            System.out.println("Writing completed");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}



