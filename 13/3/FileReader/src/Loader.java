import javax.swing.*;

public class Loader {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            MainForm form = new MainForm();
            JFrame frame = new JFrame();
            frame.setContentPane(form.getRootPanel());
            frame.setSize(550, 600);
//            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}
