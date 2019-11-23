import javax.swing.*;

public class Loader
{
    public static ShortForm sform = new ShortForm();
    public static Form form = new Form();
    public static JFrame fioFrame = new JFrame();
    public static JFrame frame = new JFrame();

    public static void main (String[] args){

        //JFrame fioFrame = new JFrame();
        //JFrame frame = new JFrame();

        //ShortForm sform = new ShortForm();
        //Form form = new Form();



        fioFrame.setContentPane(sform.getRootPanel());
        frame.setContentPane(form.getRootPanel());




        fioFrame.setLocation(300, 400);
        fioFrame.setSize(350, 100);
        fioFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fioFrame.setTitle("Window 1");
        fioFrame.setVisible(true);

        frame.setLocation(300, 100);
        frame.setSize(270, 140);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Window 2");
        frame.setVisible(false);






    }
}
