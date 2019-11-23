import java.util.function.Consumer;
import java.util.function.Predicate;

public class Lambddd {

    interface Tes{
        //void test(T i1, T i2);
        int test(int i, int g);
    }
    interface Dt{
        int stepen(int x);
    }



    public static int q (Tes t, Dt dt, int i, int g) {

        return dt.stepen(t.test(i,g));
    }


    public static void main(String[] args) {

        int ae = q((x,y)-> x+y, x -> x*x, 7,3);
        System.out.println(ae);

        Consumer<Integer> c = y -> System.out.println("1");
        c.accept(1);
        Predicate <Integer>p = x -> x>0;
        System.out.println(p.test(5));

    }
}
