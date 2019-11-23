import javax.swing.*;

public class Loader{
    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();

                JFrame frame = new JFrame();
                frame.setContentPane(timer.getRootPanel());
                frame.setTitle("Stopwatch");
                frame.setSize(200,120);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }


}
