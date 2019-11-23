import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Loader
{
    public static void main(String[] args) throws Exception
    {
//        int a = 0;
//        do
//            {
//            char ch = (char) a;
//            String c = Character.toString(ch);
//            System.out.println(ch + "\tПод номером: "+ a);
//            a++;
//            } while (a<513);






        String text2 = "Вася заработал 5000 рублей, Петя - 7563 рублей, а Маша - 30000 рублей";
        String textEdit = text2.replaceAll("[^\\s0-9\\s]","");
        String textreg[] = textEdit.trim().split("\\s+");

        int total = Integer.parseInt(textreg[0]) + Integer.parseInt(textreg[1]) + Integer.parseInt(textreg[2]);
        System.out.println(textEdit);
        System.out.println("Всего: " + total);


//        String text = "Вася заработал 5000 рублей, Петя - 7563 рублей, а Маша - 30000 рублей";
//        int vasya1 = text.indexOf("л ") +2 ;
//        int vasya2 = text.indexOf(" рублей") ;
//        int petya1 = text.indexOf("- ")+2;
//        int petya2 = text.indexOf(" рублей", vasya2+3);
//        int masha1 = text.indexOf("- ", petya2) +2;
//        int masha2 = text.lastIndexOf(" рублей");
//        System.out.println(text.substring(vasya1,vasya2)+"\t"+text.substring(petya1,petya2)+"\t"+text.substring(masha1,masha2));
//        int vasya = Integer.parseInt(text.substring(vasya1,vasya2));
//        int petya = Integer.parseInt(text.substring(petya1,petya2));
//        int masha = Integer.parseInt(text.substring(masha1,masha2));
//        System.out.println("Total income: "+vasya+petya+masha);





        System.out.println("Please, enter full name with format: Surname Name Patronimic");
        BufferedReader fullName = new BufferedReader(new InputStreamReader(System.in));
        String name = fullName.readLine().trim();

        String nameEdit[] = name.split("\\s+");
        System.out.println("Фамилия: " + nameEdit[0] + "\nИмя: " + nameEdit[1] + "\nОтчество: " + nameEdit[2]);


//        System.out.println("Please, enter full name");
//        BufferedReader fio = new BufferedReader(new InputStreamReader(System.in));
//        String str = fio.readLine().trim();
//        String surname = str.substring(0, str.indexOf(' '));
//        String name = str.substring(str.indexOf(' ')+1, str.lastIndexOf(' '));
//        String patronymic = str.substring(str.lastIndexOf(' '));
//        System.out.println("Фамилия:  \t"+surname+"\nИмя:  \t\t"+name+"\nОтчество:\t"+patronymic);


        System.out.println("\n\n\n\n\n\t\t\tPress any key to continue.");
        BufferedReader next = new BufferedReader(new InputStreamReader(System.in));
        next.readLine();




//      Не удалось избавится от обратных слешей, где они стоят больше чем по одному.
        String re = "The backslash character ('\\') serves to introduce escaped constructs, as defined in the table above, as well as to quote characters that otherwise would be interpreted as unescaped constructs. Thus the expression \\\\ matches a single backslash and \\{ matches a left brace.\n" +
                "\n" +
                "It is an error to use a backslash prior to any alphabetic character that does not denote an escaped construct; these are reserved for future extensions to the regular-expression language. A backslash may be used prior to a non-alphabetic character regardless of whether that character is part of an unescaped construct.\n" +
                "\n" +
                "Backslashes within string literals in Java source code are interpreted as required by The Java™ Language Specification as either Unicode escapes (section 3.3) or other character escapes (section 3.10.6) It is therefore necessary to double backslashes in string literals that represent regular expressions to protect them from interpretation by the Java bytecode compiler. The string literal \"\\b\", for example, matches a single backspace character when interpreted as a regular expression, while \"\\\\b\" matches a word boundary. The string literal \"\\(hello\\)\" is illegal and leads to a compile-time error; in order to match the string (hello) the string literal \"\\\\(hello\\\\)\" must be used.";
        String reEdit[] = re.replaceAll("[^A-z\\s*]","").split("\\s");
        int length = reEdit.length;
        for (int i=0; i<length; i++) {
            System.out.println(reEdit[i]);
        }









    }
}