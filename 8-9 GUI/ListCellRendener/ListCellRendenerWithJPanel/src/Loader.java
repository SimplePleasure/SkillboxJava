import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Loader {

    public static void main(String[] args) {

        String[] counties = new String[]{"Албания", "Антигуа", "Англия", "Белиз",
                "Бенин", "Ботсвана", "Барбадос", "Бразилия"};
        double[] population = new double[] {2.85, 0.1, 56, 0.38, 11.5, 2.25, 0.28, 209.5};
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



//        DefaultListModel<JPanel> model = new DefaultListModel<>();
//        for (int i=0; i<8; i++) {
//            CountryPanel country = new CountryPanel(counties[i], Integer.toString(i), iconStorage[i]);
//            model.add(i, country.getRootPanel());
//        }
        DefaultListModel<CountryPanel> model = new DefaultListModel<>();
        for (int i=0; i<8; i++) {
            CountryPanel country = new CountryPanel(counties[i], population[i], iconStorage[i]);
            model.add(i, country);
        }



        new Viev(model);


    }
}
