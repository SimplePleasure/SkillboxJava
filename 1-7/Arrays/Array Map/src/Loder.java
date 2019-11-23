import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Loder {
    public static void main (String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

            //========================================================================================
//        HashMap<String, String> base = new HashMap<>();
//        for(;;) {
//            System.out.println("Enter the number:");
//            String command = reader.readLine().trim();
//            if (base.get(command) != null) {
//                System.out.println(command +"\t"+ base.get(command));
//            } else if (base.get(command) == null) {
//                System.out.println("Number is not in base, please, enter the name");
//                String name = reader.readLine().trim();
//                base.put(command, name);
//            }
//        }
            //========================================================================================

        // Так как ключами являются номера телефонов не смог сделать вывод абонентов в этой же карте данных,
        // Добавил TreeSet, через который уже команда LIST выводит Абонентов в алфавитном порядке.
        HashMap<String, String> phoneBook = new HashMap<>();
        TreeSet<String> phoneNumbers = new TreeSet<>();

        for(;;) {
            System.out.println("Type number or name:");
            String str = reader.readLine().trim();
            if (str.equals("LIST")){
                for (String keys : phoneBook.keySet()){
                    phoneNumbers.add(phoneBook.get(keys)+" "+keys);
                }
                for (String list : phoneNumbers){
                    System.out.println(list);
                }

            }
            String check = str.replaceAll("[^0-9]", "");
            if (check.isEmpty())
            {
                if (phoneBook.containsValue(str) == false) {
                    System.out.println("Name out of phone base. Please, add phone of abonent:");
                    String number = reader.readLine();
                    phoneBook.put(number, str);
                } else {
                    for (String number : phoneBook.keySet()){
                        if (str.equals(phoneBook.get(number))){
                            System.out.println(str + " : " + number);
                        }
                    }
                }

            } else {
                if (phoneBook.get(str) != null) {
                    System.out.println(phoneBook.get(str) + " : " + str);
                } else {
                    System.out.println("Name with number "+ str +" out of base. Please enter the abonent's name:");
                    String abonentName = reader.readLine().trim();
                    phoneBook.put(str, abonentName);
                }
            }
        }






    }
}
