import java.util.concurrent.*;

public class Attempt {

    static char[] letters = {'a', 'в', 'е', 'к', 'м', 'н', 'о', 'р', 'с', 'у', 'т', 'х'};
    Queue<String> queue;
    volatile boolean isCompleted = false;

    long in = 0;
    long out = 0;
    Object test = new Object();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Attempt attempt = new Attempt();
        attempt.queue = new Queue<>(10);
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
        System.out.println("Completed. " + (System.currentTimeMillis()-start) + " ms.");
        System.out.println("Objects input: " + attempt.in + "\tObjects output: " + attempt.out);
    }

    void generate(int reg) {

        char ch = 'a';
        try {
            for (int num = 1; num < 1000; num++) {

                String n = String.format("%s%04d%s%s%d", ch, num, ch, ch, reg);
                if (queue.addElement(n)) {
                    System.out.println("Element added:\t" + n);
                    synchronized (test) {
                        in++;
                    }
                }
                Thread.sleep(1000);
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
                synchronized (test) {
                    out++;
                }
                Thread.sleep(1500);
            } catch (InterruptedException ignore) {}
        }
    }
}
