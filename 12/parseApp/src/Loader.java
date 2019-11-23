

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Loader {
    public static void main(String[] args) throws IOException {




        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Screen screen = new Screen();
                JFrame frame = new JFrame();
                frame.setSize(new Dimension(800, 600));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setContentPane(screen.getRootPanel());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });




    }
}
