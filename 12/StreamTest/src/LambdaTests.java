import java.util.ArrayList;
import java.util.List;

public class LambdaTests {
    Sex gender;
    String name;
    String country;
    int age;


    LambdaTests(Sex gender, String name, String country, int age) {
        this.gender = gender;
        this.name = name;
        this.country = country;
        this.age = age;
    }

    enum Sex {
        male, female
    }

    interface GetCountry<T> {
        @FunctionalInterface
        interface CheckPreson<T> {
            boolean check(T t);
        }
        String get(T t);
    }
    interface Output<T> {
        void out(String str);
    }





    public static void doSomeWork(List<LambdaTests> users, GetCountry.CheckPreson<LambdaTests> check, GetCountry<LambdaTests> get, Output<String> out) {
        for( LambdaTests l: users) {
            if(check.check(l)) {
                //System.out.format("%s",get.get(l));
                out.out(get.get(l));
            }
        }
    }


    public static void main (String[] args) {



        List<LambdaTests> users = new ArrayList<>();
        users.add(new LambdaTests(LambdaTests.Sex.male, "Piter", "USA", 19));
        users.add(new LambdaTests(LambdaTests.Sex.female, "Karolina", "Russia", 23));
        users.add(new LambdaTests(LambdaTests.Sex.male, "Lemon", "Russia", 21));
        users.add(new LambdaTests(LambdaTests.Sex.male, "PiDer", "USA", 16));
        users.add(new LambdaTests(LambdaTests.Sex.female, "Alina", "Russia", 22));
        users.add(new LambdaTests(LambdaTests.Sex.male, "daemon", "Russia", 28));



        //doSomeWork(users, user -> user.age < 30, user -> user.country, System.out::println );

    }



}
