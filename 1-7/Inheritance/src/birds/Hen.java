package birds;

import java.util.Comparator;
import java.util.List;

public class Hen extends NotFlying
{

    private double weight;
    public Hen()    {
        weight = 10+100*Math.random();
        setWeight(weight);
    }


    public void dabGrains() {

    }

    public void eat() {
        dabGrains();
        ingest();
        digest();
    }

    @Override
    public void voice() {
        System.out.println("Ko-ko. My weight is: " + getWeight() + " gramms.");
    }




}

