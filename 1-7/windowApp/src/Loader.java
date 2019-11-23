import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

public class Loader
{
    public static void main(String[] args)
    {
        //создание окна
        JFrame frame = new JFrame();


        //RANDOM=============================================================
        GUIbutton2 button2 = new GUIbutton2();
        frame.setContentPane(button2.getRootPanel2());
        //===================================================================


        //Создание кнопки с помощью графического редактора(создание объекта класса граф.редактора)
//        Form form = new Form();
//        frame.setContentPane(form.getRootpanel());


//        frame.setLayout(new FlowLayout());
//        JButton buttonChangeColor = new JButton();
//        buttonChangeColor.setText("Change pane color");
//        buttonChangeColor.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              frame.getContentPane().setBackground(Color.GREEN);
//            }
//        });
//        frame.add(buttonChangeColor);


//        frame.setLayout(new FlowLayout());
//        JButton button = new JButton();
//        button.setText("Click me!");
//        frame.add(button);


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Our first window App");
        frame.setVisible(true);
        System.out.println("Hello");
    }
}
