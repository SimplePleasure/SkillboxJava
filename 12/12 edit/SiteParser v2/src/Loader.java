import javax.swing.*;

public class Loader {
    public static void main (String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                GraphicInterface graphic = new GraphicInterface();
                JFrame frame = new JFrame();
                frame.setContentPane(graphic.getRootPanel());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });



    }

}
