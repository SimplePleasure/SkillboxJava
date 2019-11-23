import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Loader {

    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> phoneBook = new HashMap<>();

        for (;;) {
            String exp = "{\n";
            System.out.println("Введите номер или имя:");
            String str = reader.readLine().trim();
            boolean num = str.replaceAll("[^0-9+]", "").length() == str.length();
            boolean sout = false;

            if (str.equals("EXPORT")) {
                int size = phoneBook.size();
                if (size>0) {
                    int serial = 0;
                    for (Integer i : phoneBook.keySet()) {
                        serial++;
                        exp += "\"" + serial + "\" : {\n\"Phone\" : " + i + ",\n\"Name\" : \"" + phoneBook.get(i) + "\"\n},\n";
                        if (serial == size) {
                            int length = exp.length();
                            exp = exp.substring(0, length-2);//-1
                            exp += "\n}";
                        }
                    }

                    System.out.println("Enter the path to folder.");
                    String path = reader.readLine() + "\\EXPORT.json";
                    PrintWriter pw = new PrintWriter(path);
                    pw.write(exp);
                    pw.flush();
                    pw.close();

                }
                continue;
            }



            for (Integer i : phoneBook.keySet()) {
                if (num) {
                    if (i == Integer.parseInt(str)) {
                        System.out.println( str + ":\t" + phoneBook.get(i));
                        sout = true;
                        break;
                    }
                } else if (!num) {
                    if (str.equals(phoneBook.get(i))) {
                        System.out.println( i + ":\t" + str);
                        sout = true;
                        break;
                    }
                }
            }
            if(sout) continue;



            if (!num) {  //На ввод имени

                System.out.println("Введите номер");
                String number = reader.readLine();
                phoneBook.put(Integer.parseInt(number), str);

            } else if (num) {

                System.out.println("Введите имя");
                String name = reader.readLine();
                phoneBook.put(Integer.parseInt(str), name);
            }



        }




    }

}
