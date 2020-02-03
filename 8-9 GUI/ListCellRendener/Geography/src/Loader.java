import javax.swing.*;

public class Loader {

    public static void main(String[] args) {
        Screen screen = new Screen();
        Handler handler = new Handler(screen);
        handler.build();

        JFrame frame = new JFrame("Пост советское пространство");
        frame.setSize(800, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(screen.getRootPanel());
        frame.setVisible(true);
    }
}
