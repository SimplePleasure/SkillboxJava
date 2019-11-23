import javax.swing.*;
import java.awt.event.MouseAdapter;

public class Loader
{



    public static JFrame registration = new JFrame();
    public static CheckIn checkIn = new CheckIn();

    public static JFrame confirm = new JFrame();
    public static Confirmation confirmation = new Confirmation();

    public static JFrame getNameframe = new JFrame();
    public static GetName getName = new GetName();

    public static JFrame main = new JFrame();
    public static MainFrame mainframe = new MainFrame();




    public static void main (String[] args) {


        JFrame frame = new ScreenChanger();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        registration.setContentPane(checkIn.getRootPanel());
        registration.setTitle("Regisration");
        registration.setSize(800, 600);
        registration.setLocationRelativeTo(null);
        registration.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registration.setUndecorated(true);
        registration.setVisible(true);






        confirm.setContentPane(confirmation.getRootPanel());
        confirm.setTitle("Confirmation");
        confirm.setSize(800, 600);
        confirm.setLocationRelativeTo(null);
        confirm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        confirm.setUndecorated(true);
        confirm.setVisible(false);



        getNameframe.setContentPane(getName.getRootPanel());
        getNameframe.setSize(800, 600);
        getNameframe.setLocationRelativeTo(null);
        getNameframe.setUndecorated(true);
        getNameframe.setVisible(false);





        main.setContentPane(mainframe.getRootPanel());
        main.setTitle("Javagram");
        main.setSize(800,600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setUndecorated(true);
        main.setVisible(false);



    }
}
