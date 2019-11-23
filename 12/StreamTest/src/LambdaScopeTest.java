import java.util.function.Consumer;

public class LambdaScopeTest {

    public int x=0;


    class FirstLevl {
        public int x=1;




        public void firstLevelsMetod(int x) {



            Consumer<Integer> myConsumer = (y) ->
            {

                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x);
                System.out.format("%d : lst.this.x, %d : lst.fl.this.x, %d : input x", LambdaScopeTest.this.x, LambdaScopeTest.FirstLevl.this.x, x);
            };
            myConsumer.accept(x);
        }
    }

    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevl fl = st.new FirstLevl();
        fl.firstLevelsMetod(23);
    }

}
