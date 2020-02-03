package tasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
//        String Streams            ********************************
        String s = "Sentence for testing STREAM";
        long res = Stream.of(s.split("")).filter(x -> x.matches("[^\\s]+")).
                                              filter(x -> x.matches("[a-z а-я]")).map(x -> x.length()).count();
        String str = Stream.of(s.split("\\s+")).map(x -> x.length()).map(String::valueOf).
                                                                            collect(Collectors.joining(" "));

        System.out.println("LowerCase letters count:\t" + res + "\nWord's lettersCount:\t" + str);


//        Reduce            ********************************
        ArrayList<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(9);
        Integer i = l.stream().reduce((sum, x) -> sum *= x).filter(x -> x<100).orElse(-1);
        System.out.println("multiply = " + i);


        int[] array = new int[] {5, 5, 5, 5, 10};
        long count = IntStream.of(array).summaryStatistics().getCount();
        Double average = IntStream.of(array).average().orElseThrow(PleaseImplementMeException::new);
        System.out.println(average);


//        *************************
//        System.out.println(Task03TruckTypes.getTypeByWeight(1000).name());
        System.out.println("\n\n\n");
        Task03TruckTypes.Truck t10 = new Task03TruckTypes.Truck(11990);
        Task03TruckTypes.Truck t7 = new Task03TruckTypes.Truck(1600);
        Task03TruckTypes.Truck t = new Task03TruckTypes.Truck(2000);
        Task03TruckTypes.Truck t1 = new Task03TruckTypes.Truck(1900);
        Task03TruckTypes.Truck t9 = new Task03TruckTypes.Truck(15900);

        ArrayList<Task03TruckTypes.Truck> list = new ArrayList<>();
        list.add(t);
        list.add(t1);
        list.add(t7);
        list.add(t9);
        list.add(t10);

        Map<Task03TruckTypes.TruckType, List<Task03TruckTypes.Truck>> map = Task03TruckTypes.groupTrucksByType(list);

        for (Map.Entry<Task03TruckTypes.TruckType, List<Task03TruckTypes.Truck>> e : map.entrySet()) {
            System.out.println(e.getKey().name());
            for (Task03TruckTypes.Truck truck : e.getValue()) {
                System.out.println(truck.maxWeightKg);
            }
        }




    }
}
