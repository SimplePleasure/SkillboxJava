import javax.swing.*;

public class Loader
{


    public static JFrame frame = new JFrame();
    public static Form form = new Form();


    public static void main(String[] args) throws Exception
    {

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        frame.setContentPane(form.getRootPanel());
        frame.setUndecorated(true);

        frame.setTitle("Home work");
        frame.setSize(355, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);



        Operation operation;
        operation = (x, y, z) -> x*y+z;
        int result = operation.calc(10, 20, 1);
        System.out.println(result);


    }


    interface Operation {
        int calc(int x, int y, int z);
    }
}
