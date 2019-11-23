import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        Form form=new Form();
        frame.setContentPane(form);

        frame.pack();
        frame.setMinimumSize(form.getSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
