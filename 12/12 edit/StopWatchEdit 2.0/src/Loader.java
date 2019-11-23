import javax.swing.*;

public class Loader
{
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame();
                Timer timer = new Timer();
                frame.setContentPane(timer.getRootPanel());
                frame.setTitle("StopWatch");
                frame.setSize(200, 110);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);



            }
        });
    }
}
