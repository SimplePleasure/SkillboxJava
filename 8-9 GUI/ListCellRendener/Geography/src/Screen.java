import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {

    private JPanel rootPanel;
    private JPanel flagPanel;
    private JPanel gerbPanel;
    private JList list;
    JLabel countryName;
    JLabel population;
    JLabel currency;
    JLabel capital;
    JLabel square;

    BufferedImage flag;
    BufferedImage gerb;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JList getList() {
        return list;
    }

    public void setImages(String countryName) {
        try {
            flag = ImageIO.read(getClass().getResource(countryName + " флаг.jpg"));
            gerb = ImageIO.read(getClass().getResource(countryName + " герб.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        gerbPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(gerb, 70, 10, null);
            }
        };

        flagPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(flag, 20, 10, null);
            }
        };
    }
}
