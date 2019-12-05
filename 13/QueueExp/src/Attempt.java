import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Attempt {

    static char[] letters = {'a', 'в', 'е', 'к', 'м', 'н', 'о', 'р', 'с', 'у', 'т', 'х'};
    Queue<String> queue;

    public static void main(String[] args) {

        Attempt attempt = new Attempt();
        attempt.queue = new Queue<>(20);

        ExecutorService ex = Executors.newFixedThreadPool(3);
        ex.execute(() -> attempt.generate());
        ex.execute(attempt::write);

        try {
            ex.shutdown();
            ex.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    void generate() {


        int reg = 78;
        for (char ch1 : letters) {
            for (char ch2 : letters) {
                for (char ch3 : letters) {
                    for (int num = 1; num < 17; num++) {
                        String n = String.format("%s%03d%s%s%d", ch1, num, ch2, ch3, reg);
                        queue.addElement(n);
                        System.out.println("Element added:\t" + n);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    break;
                }
                break;
            }
            break;
        }
        queue.addElement("exit");
    }





    void write() {

           while(true) {
               String num = queue.getFirst();
               if (num.equals("exit")) break;
               System.out.println("\t\tElement taken\t" + num);

               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   return;
               }
           }

    }



}
