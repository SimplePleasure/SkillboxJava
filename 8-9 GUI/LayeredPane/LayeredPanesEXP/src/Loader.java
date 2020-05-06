import javax.swing.*;

public class Loader {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
        JFrame frame = new ScreenChanger();
        });
    }
}
