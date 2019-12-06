import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.*;

public class Attempt {

    static char[] letters = {'a', 'в', 'е', 'к', 'м', 'н', 'о', 'р', 'с', 'у', 'т', 'х'};
    Queue<String> queue;
    volatile boolean isCompleted = false;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Attempt attempt = new Attempt();
        attempt.queue = new Queue<>(6);
        ExecutorService ex = Executors.newFixedThreadPool(4);

        Future generate1 = ex.submit(() -> attempt.generate(78));
        Future generate2 = ex.submit(() -> attempt.generate(178));
        Future writing = ex.submit(attempt::write);
        Future writing2 = ex.submit(attempt::write);


        try {
            generate1.get();
            generate2.get();

            attempt.isCompleted = true;
            writing.cancel(true);
            writing2.cancel(true);

            ex.shutdown();
            ex.awaitTermination(1, TimeUnit.MINUTES);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-start + "\nCompleted. QueueSize: " + attempt.queue.size() );
    }


    void generate(int reg) {


        char ch = 'a';
        try {
            for (int num = 1; num < 100; num++) {

                String n = String.format("%s%03d%s%s%d", ch, num, ch, ch, reg);
                if (queue.addElement(n)) System.out.println("Element added:\t" + n);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }





    void write() {

        while (!isCompleted || queue.size() > 0) {
            try {
                String num = queue.getFirst();
                System.out.println("\t\tElement taken\t" + num);
                //Thread.sleep(100);
            } catch (InterruptedException ignore) {}
        }
    }



}
