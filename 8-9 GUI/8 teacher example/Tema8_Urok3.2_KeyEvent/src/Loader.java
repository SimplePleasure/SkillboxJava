//Самостоятельно изучить, как обрабатывать сочетания клавиш.

import javax.swing.*;

public class Loader {
    public static void main(String[] args) {

        JFrame frame=new JFrame();
        Form form=new Form();
        frame.setContentPane(form);
        frame.pack();
        frame.setMinimumSize(frame.getMinimumSize());
       // frame.setSize(800,600);
        frame.setVisible(true);
        frame.setTitle("Proba JOption");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
