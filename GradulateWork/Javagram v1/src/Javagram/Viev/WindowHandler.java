package Javagram.Viev;

import Javagram.interfaces.MainInterface;

import javax.swing.*;
import java.awt.*;

public class WindowHandler extends JFrame {
    public static JFrame frame = new JFrame();


    public static void frameInitialization() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(new Dimension(800, 600));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//            frame.setVisible(true);

        });
    }

    public static void setContainer(MainInterface.IViev viev) {
        frame.setContentPane(viev.getRootPanel());
        frame.setVisible(true);
//        frame.repaint();
//        frame.revalidate();
    }
}
