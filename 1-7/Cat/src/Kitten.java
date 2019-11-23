public class Kitten {
   private Double weight;
   private Double originWeight;
   private Double minWeight;






    public Kitten()
    {
        Cat.count++;
        weight = 100 + 100 * Math.random();
        originWeight = weight;
        minWeight = 100.0;
    }

    public String kittenGetStatus()
    {
        if (weight > originWeight + 30) { return "Sleeping..."; }
        else if (weight > minWeight && weight < originWeight + 30) { return "Playing..."; }
        else { return "Time to eating"; }


    }
    public double kittenGetWeight()
    {
        return weight;
    }
}
