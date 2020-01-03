import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {


            MainForm mf = new MainForm();
            JFrame f = new JFrame();
            f.setContentPane(mf.getRootPanel());
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(900, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);


        });
    }
}
