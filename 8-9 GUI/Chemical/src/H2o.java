import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class H2o {
    private JPanel rootPanel;
    private JTextArea textArea1;
    private JPanel waterPanel;
    BufferedImage water;

    H2o () {

        try {
//            water = ImageIO.read(new File("./res/water.jpg"));
            water = ImageIO.read(getClass().getResource("water.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea1.setText("Вода является хорошим растворителем. Благодаря полярности молекул и способности образовывать\n " +
                "водородные связи вода легко растворяет ионные соединения (соли, кислоты, основания)");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        waterPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(water, 0, 0, null);
            }
        };
    }
}
