import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Loader {

    public static void main (String[] args) throws IOException {




        // Не получилось настроить правильно кодировку, и русские буквы неправильно печатались, заменил их английскими.
        // Заморачиваться сильно не стал. С другими входными данными таблица может рисоваться неправильно.
        // Отформатированный файл появится в папке formatTable\res.



        String path = "C:\\Users\\Костя\\Desktop\\Projects\\10\\formatTable\\res\\0112a96fd7e446e09ff67c9171a02fee.txt";
        BufferedReader reader = new BufferedReader(new FileReader(path));

        int strQuantity = 0;
        for (;;) {
            if (reader.readLine() != null) {
                strQuantity++;
            } else {break;}

        }

        BufferedReader read = new BufferedReader(new FileReader(path));
        String[][] table = new String [strQuantity] [];
        for (int x=0; x<strQuantity; x++) {
            table[x] = read.readLine().split("\\t");
        }


        String str = "";
        String finalPath = "C:\\Users\\Костя\\Desktop\\Projects\\10\\formatTable\\res\\newTxt.txt";
        PrintWriter pw = new PrintWriter(finalPath);
        for (int i=0; i<strQuantity; i++) {
            for (int ii=0; ii<table[i].length; ii++) {

                if (ii == 0 && i!=0) {
                    str = "\n";
                }

                if (i == 0 && ii==0 ) {
                    str+= "\t\t";
                }
                if (table[i][ii].length()== 3 &&  ii!=0) {
                    str+= table[i][ii] + "\t\t\t|\t";
                }else if (table[i][ii].length()<8 && ( ii!=0)) {
                    str += table[i][ii] + "\t\t|\t";
                }else {
                    str += table[i][ii] + "\t|\t";
                }

                if (ii==table[i].length-1) {
                    pw.write(str);
                }
            }
        }
        pw.flush();
        pw.close();
    }
}
