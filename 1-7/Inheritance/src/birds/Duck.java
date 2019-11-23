package birds;

import java.util.Comparator;

public class Duck extends Flying
{

    private double weight;
    public Duck() {
        weight = 10+100*Math.random();
        setWeight(weight);
    }


    public void catchFish()
    {

    }
    public void swim()
    {

    }
    public void eat()
    {
        swim();
        catchFish();
        ingest();
        digest();

    }

    @Override
    public void voice() {
        System.out.println("Krya-Krya. My weith is: " + getWeight() + " gramms");
    }


    @Override
    public void fly() {

    }



}

