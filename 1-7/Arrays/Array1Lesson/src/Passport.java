import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

public class Passport  {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static TreeMap<String,String>pasportDataBase = new TreeMap<>();


    public static void newPerson(String key, String value){
pasportDataBase.put(key,value);
    }

    public static void check(String pasportNumber) throws IOException {
       Boolean check = pasportDataBase.containsKey(pasportNumber);
       if (check == true){
           viev(pasportNumber);
       }else{
           System.out.println("Please, type full name:");
           String name = reader.readLine().trim();
           newPerson(pasportNumber, name);
       }

    }

    public static void viev(String number){
        System.out.println("Number:\t" + number + "\t Full name: \t" + pasportDataBase.get(number));
    }
    public static boolean checkNumber(String number){
        return pasportDataBase.containsKey(number);
    }






}

