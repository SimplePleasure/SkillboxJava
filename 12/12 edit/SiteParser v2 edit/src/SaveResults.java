import java.io.FileNotFoundException;
import java.io.PrintWriter;

class SaveResults  {

    private SaveResults () {}

    static void save(String path, String result) {
         try (PrintWriter pw = new PrintWriter(path)) {
             pw.write(result);
             pw.flush();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
    }


}
