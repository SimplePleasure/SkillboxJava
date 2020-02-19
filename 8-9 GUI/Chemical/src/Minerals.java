import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Minerals {
    private JPanel rootPanel;
    private JPanel classifier;
    BufferedImage classify;

    Minerals () {
        try {
//            classify = ImageIO.read(new File("./res/351.jpg"));
            classify = ImageIO.read(getClass().getResource("351.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {

        classifier  = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(classify, 100, 0, null);
            }
        };

    }
}
