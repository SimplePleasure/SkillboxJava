import java.util.Collection;

/**
 * Created by Danya on 18.02.2016.
 */
public class Adder extends Thread
{
    private Collection list;
    private int count;

    public Adder(Collection list, int count)
    {
        Loader.onThreadCreated();
        this.list = list;
        this.count = count;
    }

    @Override
    public void run() {

        for (int i = 0; i < count; i++) {
            list.add(Math.random());
        }

        Loader.onThreadFinished();
    }
}
