//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.concurrent.TimeUnit;
//
//public class Loader {
//    public static void main(String[] args) throws IOException {
//
//
//        // Только дописав код понял, что номера имеющие обычные цифры и одинаковые буквы в список не попадают,
//        // а так же в номера не включены регионы, ноут умирает их генерировать.
//
//
//
//
//        ArrayList<String>carNumber = new ArrayList<>();
//
//        int number;
//        for (number=1; number<1000; number++)
//        {
//            if(number>99)
//            {
//                String base[] = Integer.toString(number).split("\\s*");
//                if ((base[0].equals(base[1]) && base[1].equals(base[2])) ||
//                        (base[0].equals(base[2])) || (base[0].equals(0) && base[1].equals(0) && Integer.parseInt(base[2]) < 10) ||
//                        ((Integer.parseInt(base[0]) + 2 == Integer.parseInt(base[1]) + 1)) && (Integer.parseInt(base[1]) + 1 == Integer.parseInt(base[2])))
//                {
//                    carNumber.add(Integer.toString(number));
//                }
//            }
//            else if (number >9 && number<100)
//            {
//                String base2[] = Integer.toString(number).split("\\s*");
//                if (base2[0].equals(base2[1]) || Integer.parseInt(base2[1]) == 0)
//                {
//                    String totalNumber = "0"+Integer.toString(number);
//                    carNumber.add(totalNumber);
//                }
//            }
//            else {
//                String totalNumber = "00" + Integer.toString(number);
//                carNumber.add(totalNumber);
//            }
//        }
//
//
//
//        ArrayList<String> seriesOfNumber = new ArrayList<>();
//
//        String series[] = {"а", "в", "е", "к", "м", "о", "р", "с", "т", "у", "х"};
//        for (int i1 = 0; i1<series.length; i1++)
//        {
//            //series[i]
//            for (int i2 = 0; i2<series.length; i2++)
//            {
//                for (int i3 = 0; i3<series.length; i3++)
//                {
//                    seriesOfNumber.add(series[i1]+series[i2]+series[i3]);
//                }
//            }
//        }
//
//
//
//        ArrayList<String> carNumbersBase = new ArrayList<>();
//        for (int i = 0; i<seriesOfNumber.size(); i++)
//        {
//            for (int j = 0; j<carNumber.size(); j++)
//            {
//                    String partOfSeries[] = seriesOfNumber.get(i).split("\\s*");
//                    String totalNumber = partOfSeries[0] + carNumber.get(j) + partOfSeries[1] + partOfSeries[2]; //+ region.get(k);
//                    carNumbersBase.add(totalNumber);
//            }
//        }
//
//
//
//
//        System.out.println("Numbers in base:\t" + carNumbersBase.size() + "\nNumber format:\t\tа001аа\n");
//        for(;;) {
//            System.out.println("Please, type the number:");
//            BufferedReader newReader = new BufferedReader(new InputStreamReader(System.in));
//            String checkNumber = newReader.readLine().trim();
//            long timeStart = System.nanoTime();
//            for (int car=0; car<carNumbersBase.size(); car++)
//            {
//                if (checkNumber.equals(carNumbersBase.get(car)))
//                {
//                    long timeEnd = System.nanoTime();
//                    long timeSearsh = (timeEnd-timeStart) ;
//
//
//
//                    System.out.println("Number\t" + checkNumber + "\tis in the base.\t Time to searsh "+ timeSearsh + " nanoseconds.");
//                }
//            }
//
//            //long binarySortStart = System.nanoTime();
//            Collections.sort(carNumbersBase);
//            //System.out.println("сортировка: "+ (System.nanoTime()-binarySortStart));
//            long binarySearshStart = System.nanoTime();
//            int searsh = Collections.binarySearch(carNumbersBase, checkNumber);
//            long binarySearsh = System.nanoTime() - binarySearshStart;
//            System.out.println( carNumbersBase.get(searsh) + " - binary search, time: " + binarySearsh);
//
//
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Loader {
    public static void main(String[] args) throws IOException {


        // Только дописав код понял, что номера имеющие обычные цифры и одинаковые буквы в список не попадают,
        // а так же в номера не включены регионы, ноут умирает их генерировать.




        ArrayList<String> carNumber = new ArrayList<>();

        int number;
        for (number=1; number<1000; number++)
        {
            if(number>99)
            {
                String base[] = Integer.toString(number).split("\\s*");
                if ((base[0].equals(base[1]) && base[1].equals(base[2])) ||
                        (base[0].equals(base[2])) || (base[0].equals(0) && base[1].equals(0) && Integer.parseInt(base[2]) < 10) ||
                        ((Integer.parseInt(base[0]) + 2 == Integer.parseInt(base[1]) + 1)) && (Integer.parseInt(base[1]) + 1 == Integer.parseInt(base[2])))
                {
                    carNumber.add(Integer.toString(number));
                }
            }
            else if (number >9 && number<100)
            {
                String base2[] = Integer.toString(number).split("\\s*");
                if (base2[0].equals(base2[1]) || Integer.parseInt(base2[1]) == 0)
                {
                    String totalNumber = "0"+Integer.toString(number);
                    carNumber.add(totalNumber);
                }
            }
            else {
                String totalNumber = "00" + Integer.toString(number);
                carNumber.add(totalNumber);
            }
        }



        ArrayList<String> seriesOfNumber = new ArrayList<>();

        String series[] = {"а", "в", "е", "к", "м", "о", "р", "с", "т", "у", "х"};
        for (int i1 = 0; i1<series.length; i1++)
        {
            //series[i]
            for (int i2 = 0; i2<series.length; i2++)
            {
                for (int i3 = 0; i3<series.length; i3++)
                {
                    seriesOfNumber.add(series[i1]+series[i2]+series[i3]);
                }
            }
        }



        TreeSet<String> carNumbersBase = new TreeSet<>();
        for (int i = 0; i<seriesOfNumber.size(); i++)
        {
            for (int j = 0; j<carNumber.size(); j++)
            {
                String partOfSeries[] = seriesOfNumber.get(i).split("\\s*");
                String totalNumber = partOfSeries[0] + carNumber.get(j) + partOfSeries[1] + partOfSeries[2]; //+ region.get(k);
                carNumbersBase.add(totalNumber);
            }
        }




        System.out.println("Numbers in base:\t" + carNumbersBase.size() + "\nNumber format:\t\tа001аа\n");
        for(;;) {
            System.out.println("Please, type the number:");
            BufferedReader newReader = new BufferedReader(new InputStreamReader(System.in));
            String checkNumber = newReader.readLine().trim();
            long timeStart = System.nanoTime();
            for (String value : carNumbersBase)
            {
                if (checkNumber.equals(value))
                {
                    long timeEnd = System.nanoTime();
                    long timeSearsh = (timeEnd-timeStart) ;
                    System.out.println("Number\t" + checkNumber + "\tis in the base.\t Time to searsh "+ timeSearsh + " nanoseconds.");
                }
            }


            long timeStart2 = System.nanoTime();
            if (carNumbersBase.contains(checkNumber)) {
                long timeend2 = System.nanoTime()-timeStart2;
                System.out.println(checkNumber + "is on base. search:" + timeend2);
            }


        }












    }
}