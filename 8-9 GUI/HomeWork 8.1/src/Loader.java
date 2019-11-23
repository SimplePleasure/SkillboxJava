import javax.swing.*;

public class Loader
{
    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Form form = new Form();
        frame.setContentPane(form.getRootPanel());


        frame.setTitle("Home work");
        frame.setSize(300, 175);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
