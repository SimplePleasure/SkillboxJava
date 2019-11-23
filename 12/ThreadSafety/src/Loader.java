import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Danya on 18.02.2016.
 */
public class Loader
{
    private static Collection<Double> values;
    private static int threadsCount = 10;
    private static int count = 10_000;

    private static int runningThreadsCount = 0;

    public static void main(String[] args)
    {
        ArrayList<Thread> threads = new ArrayList<>();
        values = new ArrayList<>();

        //Creating threads
        for(int i = 0; i < threadsCount; i++)
        {
            threads.add(new Adder(values, count));
        }

        //Starting threads
        for(Thread thread : threads) {
            thread.start();
        }
    }

    //===========================================================

    public synchronized static void onThreadCreated()
    {
        runningThreadsCount++;
    }

    public synchronized static void onThreadFinished()
    {
        runningThreadsCount--;
        if(runningThreadsCount == 0) {
            System.out.println(values.size());
        }
    }
}
