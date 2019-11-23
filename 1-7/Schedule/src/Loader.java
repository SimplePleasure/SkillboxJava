import javax.swing.*;

public class Loader {

    public static void main (String[] args) {



        Graphic gr= new Graphic();
        JFrame frame = new JFrame();
        frame.setContentPane(gr.getRootPanel());
        frame.setSize(240,365);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
