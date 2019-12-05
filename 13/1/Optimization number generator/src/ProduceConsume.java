import java.io.*;
import java.util.concurrent.*;

public class ProduceConsume {

    volatile boolean isDone = false;
    LinkedBlockingDeque<StringBuilder> deque;


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        final ProduceConsume produceConsume = new ProduceConsume();
        Future produce = ex.submit(produceConsume::generate);
        Future consume = ex.submit(produceConsume::writeNums);
        
        produce.get();
        produceConsume.isDone = true;
        consume.cancel(true);


        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(System.currentTimeMillis()-start);
    }


    void generate() {
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
                } catch (InterruptedException ignore) {}
            }
            if (Thread.currentThread().isInterrupted()) {
                Thread.interrupted();
            }
            System.out.println("writing...");
            fos.getChannel().force(true);
            System.out.println("Writing completed");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}



