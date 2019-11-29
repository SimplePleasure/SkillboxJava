
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;



/*
Сделано по примерам из:
https://www.codeflow.site/ru/article/java-blocking-queue
 */


public class Loader
{
   ArrayList<String> months;
   LinkedBlockingDeque<String> deque;
   final String posionPill = "exit";

    public static void main(String[] args) throws InterruptedException {

        //BlockingDeque bd = new BlockingDeque();
        Loader l = new Loader();
        l.months = new ArrayList<>();

        l.months.add("Январь");
        l.months.add("Февраль");
        l.months.add("Март");
        l.months.add("Апрель");
        l.months.add("Май");
        l.months.add("Июнь");
        l.months.add("Июль");
        l.months.add("Август");
        l.months.add("Сентябрь");
        l.months.add("Октябрь");
        l.months.add("Ноябрь");
        l.months.add("Декабрь");

        l.deque = new LinkedBlockingDeque<>(2);
        ExecutorService ex = Executors.newFixedThreadPool(2);

        ex.execute(l::producer);
        ex.execute(l::consumer);
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.MINUTES);



    }
    public void producer() {

        Iterator<String> iter = months.iterator();

        try {
            while (iter.hasNext()) {
                String month = iter.next();
                Thread.sleep((int)(Math.random()*1000));
                deque.put(month);
            }
            deque.put(posionPill);
        } catch (InterruptedException ignore) {}
    }

    public void consumer() {

        try {
            while (true) {

                String month = deque.take();

                if (!month.equals("exit")) {
                    Thread.sleep((int) (Math.random() * 1000));
                    System.out.println(month);
                } else {
                    System.out.println("\n\tDone.");
                    break;
                }
            }
        } catch (InterruptedException ignore) {}
    }

}
