import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Loader {

    public static void main(String[] args) {

        String[] counties = new String[]{"testStr1", "testStr2", "testStr3", "testStr4",
                "testStr5", "testStr6", "testStr7", "testStr8"};
        ImageIcon[] iconStorage = new ImageIcon[8];
        for (int i = 0; i < 8; i++) {
            File f = new File("./res/" + (i+1) + ".png");
            try (FileInputStream fis = new FileInputStream(f)) {
                byte[] ico = fis.readAllBytes();
                iconStorage[i] = new ImageIcon(ico);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        DefaultListModel<JPanel> model = new DefaultListModel<>();
        for (int i=0; i<8; i++) {
            CountryPanel panel = new CountryPanel(counties[i], Integer.toString(i), iconStorage[i]);
            model.add(i, panel.getRootPanel());


        }
        new Viev(model);


    }
}
