import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lipids {
    private JPanel rootPanel;
    private JPanel classifier;
    private JScrollPane scroll;
    BufferedImage img;


    Lipids() {
        try {
            img = ImageIO.read(getClass().getResource("lipid.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        classifier = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        };
    }
}
