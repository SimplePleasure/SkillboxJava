package birds;

public class Frog implements  Verebrate
{

    private double weight;
    public Frog() {
        weight = 10+20*Math.random();
    }


    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void voice() {
        System.out.println("Kwa-kwa. My weight is: " + getWeight() + "gramms");
    }

    @Override
    public Double getWeight() {
        return this.weight;
    }
}
