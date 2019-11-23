import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test2 {

    /*
    Класс Test2

    Хотел поэксперементировать сделав генерацию номеров в несколько потоков и записью в 1 файл, но что-то не получается...
    Среда разработки смеётся надо мной :/
    Exception in thread "pool-1-thread-2" java.lang.StringIndexOutOfBoundsException
    Если разкоментить 79 строку все символы печатаются нормально, а без неё вылетают ошибки..
     */








    boolean isGenering;
    long startGenerating;
    //ExecutorService ex;
//    StringBuilder buffer;
    LinkedList<StringBuilder> list;

    public static void main (String[] args) {

        Test2 t = new Test2();


        new Thread(new Runnable() {
            @Override
            public void run() {
                t.produce();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.consume();
            }
        }).start();
    }

    void produce () {

        startGenerating = System.currentTimeMillis();
        isGenering = true;
        list = new LinkedList<>();
        ExecutorService ex = Executors.newFixedThreadPool(3);

        char[] letters = {'a', 'в', 'е', 'к', 'м', 'н', 'о', 'р', 'с', 'у' , 'т', 'х'};
        int[] region = {47, 78, 98, 147, 178, 198, 77, 99, 199, 750};


        for (int reg: region) {
            ex.execute(() -> {
                StringBuilder buffer = new StringBuilder();
                for (int num=1; num<1000; num++) {
                    for (char ch1: letters) {
                        for (char ch2: letters) {
                            for (char ch3: letters) {



                                if (buffer.length()> 1_000_000) {
                                    synchronized (this) {
                                        list.add(buffer);
                                        notifyAll();
                                    }
                                    buffer = new StringBuilder();
                                }


//                                String carNum = String.format("%03d", num);   //===========================================================
                                buffer.append(ch1);
                                if (num< 10) {
                                    buffer.append("00");
                                } else if (num< 100) {
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
                    notifyAll();
                }
            });
        }
        try {
            ex.shutdown();
            ex.awaitTermination(1, TimeUnit.MINUTES);
            isGenering = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    void consume() {

            try (PrintWriter pw = new PrintWriter("res/test2.txt")) {

                while (isGenering || list.size() > 0) {
                    System.out.println(list.size());
                    while (list.size() == 0) {
                        synchronized (this) {
                            wait();
                        }
                    }

                    synchronized (this) {
                        pw.write(list.getFirst().toString());
                        list.removeFirst();
                    }
                }
                System.out.println(System.currentTimeMillis() - startGenerating);
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

    }


}
