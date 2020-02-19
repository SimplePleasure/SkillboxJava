import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Nucleic {
    private JPanel rootPanel;
    private JPanel dnkPanel;
    private JPanel rnkPanel;
    private JTextArea dnkDescriptoin;
    private JScrollPane scroll;
    BufferedImage dnk;
    BufferedImage rnk;

    Nucleic () {
        scroll.getHorizontalScrollBar().setVisible(false);
        dnkDescriptoin.setText("Нуклеотиды входящие в состав дезоксирибонуклеиновой кислоты содержат дезоксирибозу, остаток\n фосфорной кислоты и одно из четырёх азотистых оснований: аденин, гуанин, цитозин или тимин");
        try {
            dnk = ImageIO.read(getClass().getResource("spiral.jpeg"));
            rnk = ImageIO.read(getClass().getResource("dnkrnk.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dnkPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(dnk, 0, 0, null);
            }
        };
        rnkPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(rnk, 120, 20, null);
            }
        };


    }
}
