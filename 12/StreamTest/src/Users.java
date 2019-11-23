import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Users {

    public enum Sex {
        male, female
    }

    Sex gender;
    String name;
    String country;
    int age;



    interface CheckPerson {
        boolean check(Users user);
    }
    interface Predicate <T> {
        boolean test (T t);
    }

    static void getPersons (Users use){
        System.out.println(use);
    }

    Users(Sex gender, String name, String country, int age) {
        this.gender = gender;
        this.name = name;
        this.country = country;
        this.age = age;
    }


    public static void printPersons( List<Users> roster, CheckPerson tester) {
        AtomicInteger counter = new AtomicInteger();
        for (Users u : roster) {

            if (tester.check(u)) {
                counter.incrementAndGet();
                System.out.format("%d%10.10s\t%d\t%s\t%s %n", counter.get(),u.name, u.age, u.gender, u.country);
            }
        }
    }
    public static void genericLambdaTest (List<Users> usersList, Predicate<Users> p){
        for (Users user: usersList){
            if (p.test(user)) {
                System.out.println(user.name);
            }
        }
    }


    public static void main (String[] args) {

        List<Users> u = new ArrayList<>();
        u.add(new Users(Sex.male, "Piter", "USA", 19));
        u.add(new Users(Sex.female, "Karolina", "Russia", 23));
        u.add(new Users(Sex.male, "Lemon", "Russia", 21));
        u.add(new Users(Sex.male, "PiDer", "USA", 16));
        u.add(new Users(Sex.female, "Alina", "Russia", 22));
        u.add(new Users(Sex.male, "daemon", "Russia", 28));



       printPersons(u, x -> x.age< 25);
        System.out.format("%n%n%n");
        genericLambdaTest(u, x -> x.age< 25);








    }








}
