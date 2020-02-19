import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Proteins {
    private JPanel rootPanel;
    private JPanel structre;

    private BufferedImage structreImage;

    Proteins () {
        try {
//            File file = new File("./res/structe.jpg");
//            structreImage = ImageIO.read(file);
            structreImage = ImageIO.read(getClass().getResource("structe.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        structre = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(structreImage, 40, 20, null);
            }
        };
    }
}
