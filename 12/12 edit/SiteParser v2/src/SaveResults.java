import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveResults {

    private String result;
    private String path;

    SaveResults (String result, String path) {
        this.result = result;
        this.path = path;
    }

    void save() {
         try (PrintWriter pw = new PrintWriter(path)) {
             pw.write(result);
             pw.flush();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
    }


}
