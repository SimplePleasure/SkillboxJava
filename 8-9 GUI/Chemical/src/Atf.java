import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Atf {
    private JPanel rootPanel;
    private JTextArea atfDescription;
    private JPanel vitamin;
    BufferedImage vita;

    Atf () {


        try {
            vita = ImageIO.read(getClass().getResource("jvrast.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        atfDescription.setText("АТФ состоит из азотистого основания аденина, углевода рибозы и трёх остатков фосфорной кислоты");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        vitamin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(vita, 0, 0, null);
            }
        };
    }
}
