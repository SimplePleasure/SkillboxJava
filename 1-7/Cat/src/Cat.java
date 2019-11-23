
public class Cat
{
    private Double originWeight;
    private Double weight;

    private Double minWeight;
    private Double maxWeight;

    private Double weightFood = 0.0;
    private Double weightDrink = 0.0;










    //============================================================================= Самостоятельная работа
    static Integer count=0;
    public static void getCount()
    {
        System.out.println(count);
    }



    public Cat(Double weight)
    {
        this.weight = weight;
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
        //Cat.count();
    }


    public void eaten()
    {
        if (weightFood != null) {
            System.out.println("Седено: " + weightFood);
        }
        if (weightDrink != null) {
            System.out.println("Выпито: " + weightDrink);
        }
    }

    public void toilet()
    {
        Double weight = Math.random()*100 + 50;
        if (this.weight>minWeight && (this.weight-weight) < minWeight) { count--; }
        this.weight = this.weight - weight;
        System.out.println("toilet - " + weight);
    }

    public void meow(Double weight)
    {
        if ( this.weight>minWeight && (this.weight - weight) < minWeight) { count--; }
        this.weight = this.weight - weight;
        System.out.println ("Вес убавился: " + weight + " meow");
    }

    public static double getWeightDifference(Cat object1, Cat object2)
    {
        Double difference = Math.abs(object1.getWeight() - object2.getWeight());
        return difference;
    }








// Клонирование котов. Вроде теперь всё верно :))
    public Cat createTwinCat()
    {
        Cat twin = new Cat(this.weight, this.originWeight);
        return twin;
    }

    public Cat(Double weight, Double originWeight)
    {
        this.weight = weight;
        this.originWeight = originWeight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

//============================================================================================











    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }


    public void setWeight (Double weight)
    {
        this.weight = weight;
        this.originWeight = weight;
    }


    public void meow()
    {
        if (weight>=minWeight && weight-1<minWeight) { count--; }
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        if ( weight < maxWeight && (weight+amount) > maxWeight) { count--; }
        weightFood = weightFood + amount;
        weight = weight + amount;
    }

    public void drink(Double amount)
    {
        if ( weight < maxWeight && (weight+amount) > maxWeight) { count--; }
        weightDrink = weightDrink + amount;
        weight = weight + amount;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getOrigrinWeight ()
    {
        return originWeight;
    }


    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

}