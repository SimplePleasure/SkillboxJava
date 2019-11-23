/**
 * Created by Danya on 18.02.2016.
 */
public class Incrementor extends Thread
{
    private Counter counter;
    private int count;

    public Incrementor(Counter counter, int count)
    {
        Loader.onThreadCreated();
        this.counter = counter;
        this.count = count;
    }

    @Override
    public void run()
    {

        for(int i = 0; i < count; i++) {
            counter.increment();
        }
        Loader.onThreadFinished();
    }
}
