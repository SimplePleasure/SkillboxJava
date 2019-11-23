import java.util.ArrayList;

/**
 * Created by Danya on 18.02.2016.
 */
public class Loader
{
    private static int threadsCount = 0;
    private static int threadPairsCount = 5;
    private static Counter counter;

    public static void main(String[] args)
    {
        counter = new Counter();
        int count = 10_000;



        //Creating threads
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for(int i = 0; i < threadPairsCount; i++)
        {
            threads.add(new Incrementor(counter, count));
            threads.add(new Decrementor(counter, count));
        }

        //Starting threads
        for(Thread thread : threads) {
            thread.start();
        }

    }

    //=========================================================================

    public static void onThreadFinished()
    {
        threadsCount--;
        if(threadsCount == 0) {
            System.out.println(counter.getValue());
        }
    }

    public static void onThreadCreated()
    {
        threadsCount++;
    }
}
