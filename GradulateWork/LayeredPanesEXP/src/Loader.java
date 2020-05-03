import javax.swing.*;

public class Loader {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

        JFrame frame = new ScreenChanger();
        frame.setTitle("Test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        });


    }
}
