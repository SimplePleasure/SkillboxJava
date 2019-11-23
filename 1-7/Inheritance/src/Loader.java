import birds.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Loader implements Comparator<Verebrate>
{
    public static void main(String[] args) {

        ArrayList<Verebrate> animals = new ArrayList<>();
        animalsADD(animals);

        Collections.sort(animals, new Loader());
        Duck duck = new Duck();
        duck.getWeight();



        for (Verebrate animal : animals){
            animal.voice();
        }


    }

    private static void animalsADD (ArrayList<Verebrate>animals){

        animals.add(new Duck());
        animals.add(new Duck());
        animals.add(new Duck());
        animals.add(new Hen());
        animals.add(new Hen());
        animals.add(new Hen());
        animals.add(new Frog());
        animals.add(new Frog());
        animals.add(new Frog());
        animals.add(new Ostrich());
        animals.add(new Ostrich());
        animals.add(new Ostrich());
        animals.add(new Sparrow());
        animals.add(new Sparrow());
        animals.add(new Sparrow());
    }

    @Override
    public int compare(Verebrate o1, Verebrate o2) {
        return (int) Math.round(o1.getWeight() - o2.getWeight());
    }
}