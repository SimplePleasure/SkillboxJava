import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.*;
import java.util.stream.Stream;

public class Loader {

    String str="jhvjhh";





    public static void main (String[] args) {


//        MultiThTest mtt = new MultiThTest();
//        try {
//            mtt.start();
//            Thread.currentThread().sleep(3000);
//            mtt.startOrResume();
//            Thread.currentThread().sleep(3000);
//            mtt.pause();
//            Thread.currentThread().sleep(3000);
//            mtt.startOrResume();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        List <String> nameList = new ArrayList<>();
//        nameList.add("Маша");
//        nameList.add("Костя");
//        nameList.add("Катя");
//        nameList.add("Аня");
//        nameList.add("Таня");
//        Stream<String> stream = nameList.stream().filter(name -> name.length() < 5).map( name -> name+" + Костя = <3");
//        stream.forEach(System.out::println);




//        ArrayList<Integer> list = new ArrayList<>();
//        IntStream.range(0,1000).filter( x -> x%2!=0).filter(x -> x%3!=0).forEach(list::add);//System.out::println);
//        for (Integer i: list) {
//            System.out.println(i);
//        }


//        Stream.generate(Math::random).map(x -> String.format("%.5f",x)).limit(20).forEach(System.out::println);

//        List<Integer> list = new ArrayList<>();
//        IntStream.range(0,100).filter(x-> x%2!=0).map( x -> x+1).forEach(list::add);



//        String[] array = new String[]{"Аня", "Маша", "Юля"};
//        Stream<String> streamOfArray = Arrays.stream(array);
//        streamOfArray.map(x -> x.split("")).flatMap(Arrays::stream).distinct()
//                .collect(Collectors.toList()).forEach(System.out::println);


//        IntStream.of(1,3,2,5,0,7).flatMap(x-> IntStream.range(0, x)).forEach(System.out::println);

//        IntStream.of(120, 410, 85, 32, 314, 12).filter(x-> x>49).sorted().limit(2).forEach(System.out::println);

//        IntStream.range(1, 100).peek(System.out::println).map(x -> x * x).forEach(System.out::println);


//        String[] array = new String[]{"Аня", "Маша", "Елизавета"};
//        Stream<String> streamOfArray = Arrays.stream(array);
//        streamOfArray.map(x -> x.split("")).flatMap(Arrays::stream).distinct()
//                .collect(Collectors.toList()).forEach(System.out::println);





//        String[] array = new String[]{"Аня", "Маша", "Юля", "Олеся", "Лиза"};
//        Arrays.stream(array).map(x -> String.format("%s + Костя = <3\t", x)).map(x -> x.repeat(x.length()-14))
//                .forEach(System.out::println);

//        String[] array = new String[]{"Аня", "Маша", "Юля", "Олеся", "Лиза"};
//        Arrays.stream(array).peek(System.out::print).map(String::length).forEach(System.out::println);





















        //System.out.println(hasSubpattern("a567bcda6bc1a3cc91d"));
    }
    public static String hasSubpattern(String string) {

        String numbers =  string.replaceAll("[^0-9]", "");
        String str = string.replaceAll("[0-9]", "");
        System.out.println(numbers + "\t" + str);
        String strResult = Arrays.stream(str.split("")).distinct().sorted().reduce((s1, s2) -> s1+s2).get();
        String numResult = Stream.of(numbers).map(s -> s.split("")).flatMap(Arrays::stream).sorted()
                    .reduce((s1, s2) -> s1 + s2).get();
        return numResult+strResult;
    }


}


