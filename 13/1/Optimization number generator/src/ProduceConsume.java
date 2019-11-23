import java.io.*;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProduceConsume {



    /*Код работает нестабильно. Бывает обгоняет однопоточный генератор, а бывает и наоборот. В среднем скорость выполнения
    такая же как при выполнении в 1 поток, может чуть быстрее.
    Иногда код не завершался, а оставался запущенным. Добавил 82 и 84 строку, вроде ошибка ушла.

     */




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
            StringBuilder buffer = new StringBuilder();

            char[] letters = {'a', 'в', 'е', 'и', 'к', 'м', 'н', 'о', 'р', 'с', 'т', 'х'};
            int[] region = {47, 78, 98, 147, 178, 198, 77, 99, 199, 750};

            for (int reg: region) {
                for (int num=1; num < 1000; num++) {
                    for (char ch1: letters) {
                        for (char ch2: letters) {
                            for (char ch3: letters) {

                                if (buffer.length() > 1_000_000) {
                                    synchronized (this) {
                                        list.add(buffer);
                                        notify();
                                    }
                                    buffer = new StringBuilder();
                                }

                                buffer.append(ch1);
                                if (num<10) {
                                    buffer.append("00");
                                }else if (num<100) {
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
            }
            isgenerating = false;
            synchronized (this) {
                list.add(buffer);
                notify();
            }

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

                FileChannel fileChannel = fos.getChannel();
                fileChannel.force(true);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis()-start);
        }
    }
}



