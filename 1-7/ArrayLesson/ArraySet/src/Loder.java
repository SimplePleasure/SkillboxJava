import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Loder {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<String> medication = new TreeSet<>();
        for (;;) {
            System.out.println("Enter add / new list / empty enter to show list");
            String command = reader.readLine();
            if (command.equals("add"))
            {
                System.out.println("Enter medication title or empty enter for exit.");
                for (;;)
                {
                    String item = reader.readLine();
                    if(item.equals("")){break;}
                    medication.add(item);

                }

            }
            else{
                for(String item : medication){
                    System.out.println(item);
                }
            }
            if (command.equals("new list")){
                medication.removeAll(medication);
                continue;
            }
        }
    }
}
