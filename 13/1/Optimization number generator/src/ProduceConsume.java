import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProduceConsume {



    public static void main (String[] args) throws InterruptedException {


        ExecutorService ex = Executors.newFixedThreadPool(2);
        final Processor processor = new Processor();
        ex.execute(processor::generate);
        ex.execute(processor::writeNums);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);

    }


    public static class Processor {

        long start;
        volatile boolean isgenerating = true;
        LinkedList<StringBuilder> list = new LinkedList<>();


        void generate() {

            start = System.currentTimeMillis();

            ExecutorService executor = Executors.newFixedThreadPool(3);





            for (int reg: GenericOrder.region) {
                executor.execute(() -> {
                    StringBuilder buffer = new StringBuilder();

                for (int num = 1; num < 1000; num++) {
                    for (char ch1 : GenericOrder.letters) {
                        for (char ch2 : GenericOrder.letters) {
                            for (char ch3 : GenericOrder.letters) {

                                if (buffer.length() > 1_000_000) {
                                    synchronized (this) {
                                        list.add(buffer);
                                        notify();
                                    }
                                    buffer = new StringBuilder();
                                }

                                buffer.append(ch1);
                                if (num < 10) {
                                    buffer.append("00");
                                } else if (num < 100) {
                                    buffer.append("0");
                                }
                                buffer.append(num)
                                        .append(ch2)
                                        .append(ch3)
                                        .append(reg)
                                        .append("\n");
                            }
                        }
                    }
                }
                    synchronized (this) {
                        list.add(buffer);
                        notify();
                    }
                });
            }
            try {
                executor.shutdown();
                executor.awaitTermination(1,TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isgenerating = false;

        }

        void writeNums() {

            try (FileOutputStream fos = new FileOutputStream("res/test.txt");
                 PrintWriter pw = new PrintWriter(fos)) {



                while (isgenerating || list.size() != 0) {
//                    System.out.println(list.size());

                    while (list.size() == 0) {
                        synchronized (this) {
                            wait();
                        }
                    }
                    pw.write(list.getFirst().toString());
                    synchronized (this) {
                        list.removeFirst();
                    }
                }

                fos.getChannel().force(true);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis()-start);
        }
    }
}



