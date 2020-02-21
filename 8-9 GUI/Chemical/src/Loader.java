import javax.swing.*;

public class Loader {



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new Handle();
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });
    }




}
