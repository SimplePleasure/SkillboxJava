
public class Loader
{
    public static void main(String[] args)

    //1. В статическом методе можно использовать внешние переменные только если они тоже статические?
    //2. Вычет кошек сделал не из метода getStatus(), т.к. колличество подсчитывалось бы не совсем верно,
    // а через прибавление или вычитание веса напрямую. И подсчёт кошек сделал чуть подругому -
            // метод
    //3. В чём разница между Double и double?
    //4. Конструктор можно добавить в том случае если его название такое же как и класс в котором он находится?
    //5. Только сейчас понял, что не так сделал задание 3.5 "Создание объектов и конструктор". нужно создать метод
    // который через конструктор создаёт котёнка весом 100-200. У меня этот метод просто перезаписывает
    // переменную weightKitten и выводит её в консоль.  А метод создания котят есть в классе Kitten.


    {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat wiskas = new Cat();
        Cat barsik = new Cat();

        cat.feed(7000.0);
        cat.drink(5.0);

        cat1.feed(1000.0);
        cat1.drink(70.0);

        cat2.feed(10.0);
        cat2.drink (50.0);


        cat.eaten();
        wiskas.toilet();
        wiskas.meow( 3200.0);
        barsik.meow( 350.0 );
        System.out.println ("Difference: " + Cat.getWeightDifference(barsik, cat));






        System.out.println(": " + cat.getWeight());
        System.out.println(": " + cat1.getWeight());
        System.out.println(": " + cat2.getWeight());
        System.out.println(": " + wiskas.getWeight());
        System.out.println(": " + barsik.getWeight());
        System.out.println("Колличество котов: " + Cat.count);

        Cat masya = new Cat(2500.0 );
        System.out.println(masya.getStatus() + " " + masya.getWeight());
        System.out.println("Колличество котов: " + Cat.count);
        //====================================================================== Kitten
        Kitten kitty = new Kitten();
        System.out.println(kitty.kittenGetWeight());
        System.out.println(kitty.kittenGetStatus());
        System.out.println("Колличество котов: " + Cat.count);
        //================================================================================
        System.out.println("cat: " + cat.getWeight());
        cat.setWeight(masya.getWeight());
        System.out.println("cat: " + cat.getWeight());

        Loader.getKitten();



        




        System.out.println("Котов: "+Cat.count);
        Cat cat3 = cat2.createTwinCat();
        System.out.println("Котов: "+Cat.count);

        cat2.feed(7000.0);
        cat2.eaten();
        System.out.println(Cat.getWeightDifference(cat2, cat3));
        Cat.getCount();




        System.out.println("cat2 weight: " + cat2.getWeight());
        System.out.println("cat2 orig: " + cat2.getOrigrinWeight());
        System.out.println("cat3 weight: " + cat3.getWeight());
        System.out.println("cat3 orig: " + cat3.getOrigrinWeight());

        Kitten newKitten = new Kitten();
        Kitten newKitten2 = new Kitten();

        System.out.print(newKitten.kittenGetWeight() + "   ");
        System.out.println(newKitten.kittenGetStatus());
        Cat.getCount();













        getKitten();



    }
    //Не стал считать этим методом колличество котов, т.к. он не создаёт объект, а в классе Kitten счётчик включён.
    public static void getKitten()
    {
       Double weightKitten = 100 + 100 * Math.random();
       System.out.println("Kitty weight: " + weightKitten);


    }
}