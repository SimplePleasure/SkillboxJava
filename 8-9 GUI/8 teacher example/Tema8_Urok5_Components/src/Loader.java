import javax.swing.*;

public class Loader {
    public static void main(String[] args) {

        JFrame frame=new JFrame();
        Form form=new Form();
        frame.setContentPane(form);

        frame.setVisible(true);
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }
}
