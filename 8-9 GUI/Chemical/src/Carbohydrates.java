import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Carbohydrates {
    private JPanel rootPanel;
    private JPanel classification;
    private JPanel function;

    BufferedImage classifier;

    Carbohydrates() {
        try {
//            File file = new File("./res/classification.jpg");
//            classifier = ImageIO.read(file);
            classifier = ImageIO.read(getClass().getResource("classification.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        function.setLayout(new BoxLayout(function, BoxLayout.Y_AXIS));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        classification = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(classifier, 20, 0, null);
            }
        };

    }
}
