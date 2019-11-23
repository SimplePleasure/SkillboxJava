public class Test
{
    interface Operation {
        int work(int x, int y, int z);
    }


    // метод add5 принимает реализацию метода work() и заменяемое значение x, возвращает выражение с заменённым x.

    static Operation add5 (Operation op, int rep) {
        Operation res = (a,b,c) -> op.work(rep, b, c);
        return res;
    }

    public static void main(String[] args) {
        Operation calc = (x, y, z) -> x+y+z;
        Operation multiply = (x, y, z) -> x*y*z;


        System.out.println("add5 calc 2 4 6\t\t C заменой: "+add5(calc, 99).work(2,4,6) + "\t Без: " +calc.work(2,4,6));
        System.out.println("add5 mult 2 4 6\t\t C заменой: "+add5(multiply, 99).work(2,4,6)+"\t Без: " +
                        multiply.work( 2, 4,6));





    }




}
