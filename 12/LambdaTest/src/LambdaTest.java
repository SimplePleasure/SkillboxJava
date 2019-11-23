import java.util.function.BiFunction;
import java.util.function.Function;





public class LambdaTest {



    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> addAtoB = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiplyAtimesB = (a, b) -> a * b;

        Function<Integer, Integer> add5toB = partial(addAtoB, 5);
        Function<Integer, Integer> multiplyOn2 = partial(multiplyAtimesB, 2);

        System.out.println("5 + 2 = " + add5toB.apply(2));
        System.out.println("7 * 2 = " + multiplyOn2.apply(7));
    }

    private static Function<Integer, Integer> partial(BiFunction<Integer, Integer, Integer> binaryOption, int n) {
        Function<Integer, Integer> partialFunction = (x) -> binaryOption.apply(x,n);
        return partialFunction;
    }
}