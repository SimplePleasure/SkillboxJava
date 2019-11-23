/**
 * Created by Danya on 18.02.2016.
 */
public class Counter
{

    private int value = 0;


    public void increment() {
        value = value + 1;
    }

    public void decrement(){
        value = value - 1;
    }

    public int getValue(){
        return value;
    }
}
