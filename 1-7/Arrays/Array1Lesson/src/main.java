import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {
    public static void main(String[] args) throws Exception {


        //1) Массив с радугой прошу не просто вывести в обратном порядке, а перевернуть, а затем вывести.

        String colorRainbow[] = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Violet"};
        String rainbowColor[] = new String[colorRainbow.length];
        for (int i = colorRainbow.length-1; i>=0; i--){
            rainbowColor[(rainbowColor.length-1)-i] = colorRainbow[i];
        }

        for (int j = 0; j<rainbowColor.length; j++){
            System.out.println(rainbowColor[j]);
        }

//=========================================================================================================

        // Не совсем понял задание, поэтому мог что-то сделать не так.


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Start to enter pasport database? yes/no:");
        if (reader.readLine().equals("yes")) {
            System.out.println("При вводе серии с номером разделяйте их пробелом");
            for (;;){
                System.out.println("Enter 'series number' or empty str to random series:");
                String data = reader.readLine().trim();
                if (data.isEmpty()) {
                        String series = Double.toString(Math.random()).substring(2, 6);
                    for(;;) {
                        System.out.println("Series: " + series + "\tPlease, enter the number:\t\t\tEmpty str to new series or 'Back'");
                        String command = reader.readLine().trim();
                        if (command.equals("Back")) {break;}
                        if (command.isEmpty()){series = Double.toString(Math.random()).substring(2, 6); continue;}
                        if (command.length()>7 && !(command.substring(0,4).equals(series))){
                            System.out.println("Change series to: " + command.substring(0,4) + "?\tempty str/no");
                            String change = reader.readLine();
                            if (change.isEmpty()) {series = command.substring(0,4); Passport.check(command); continue;}
                            Passport.check(command); continue;
                        }
                        if (command.length()>7 && command.substring(0,4).equals(series)){Passport.check(command);continue;}
                        String number = series + " " + command;
                        Passport.check(number);
                    }
                } else if (data.equals("Exit")) {
                    break;
                } else {
                    Passport.check(data);
                }
            }
        }












        //45 07 *** ***
//        String numb = "4507000000";
//
//        String n[] = new String[1000000];
//        for(int i = 0; i<1000000; i++)
//        {
//            long series = Long.parseLong(numb) + i;
//            n[i]= Long.toString(series);
//        }
//
//
//
//        for (int j = 0; j<1000000; j++)
//        {
//            System.out.println(n[j]);
//        }


//        String chessDesk[][]= {
//                {"a1\t", "a2\t", "a3\t", "a4\t", "a5\t", "a6\t", "a7\t", "a8\t"},
//                {"b1\t", "b2\t", "b3\t", "b4\t", "b5\t", "b6\t", "b7\t", "b8\t"},
//                {"c1\t", "c2\t", "c3\t", "c4\t", "c5\t", "c6\t", "c7\t", "c8\t"},
//                {"d1\t", "d2\t", "d3\t", "d4\t", "d5\t", "d6\t", "d7\t", "d8\t"},
//                {"e1\t", "e2\t", "e3\t", "e4\t", "e5\t", "e6\t", "e7\t", "e8\t"},
//                {"f1\t", "f2\t", "f3\t", "f4\t", "f5\t", "f6\t", "f7\t", "f8\t"},
//                {"g1\t", "g2\t", "g3\t", "g4\t", "g5\t", "g6\t", "g7\t", "g8\t"},
//                {"h1\t", "h2\t", "h3\t", "h4\t", "h5\t", "h6\t", "h7\t", "h8\t"}
//        };
//        for (int i = 0; i<chessDesk.length; i++)
//        {
//            for (int j = 0; j<chessDesk.length; j++)
//            {
//                System.out.print(chessDesk[i][j]);
//                if(j == 7) {
//                    System.out.println("\n");
//                }
//            }
//
//        }



        //2) Шахматы создайте без использования заранее заданного двумерного массива. Используйте цикл и для создания
        // элементов и для вывода

        String atribute[] = {"a","b","c","d","e","f","g","h"};
        for (int i = 0; i<8; i++){
            for (int j = 1; j<9; j++){
                System.out.print(atribute[i]+j + "\t");
                if (j==8){System.out.println("\n");}

            }
        }









    }
}




