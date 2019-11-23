import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveResults extends Thread
{
    String UrlMap;
    String path;
    SaveResults(String map, JTextField field) {
          UrlMap = map;
          path = field.getText();
    }


    @Override
    public void run() {

        if (!path.isEmpty()) {
            super.run();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pw.write(UrlMap);
            pw.flush();
            pw.close();

        }

    }
}
