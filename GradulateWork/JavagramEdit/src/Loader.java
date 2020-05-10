import javax.swing.*;
import java.awt.event.MouseAdapter;

public class Loader
{


    public static void main (String[] args) throws Exception {

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new ScreenChanger();


        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
