import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class SetForm extends JFrame
{

    MyForm mf = new MyForm();

    {
        setContentPane(mf.getRootPanel());
        setMinimumSize(new Dimension(300, 100));
        mf.setButton( e -> setTextPane());
        mf.getMove( e -> move());
    }
    public void setTextPane(){
        mf.textField1.setText("eee");
    }

    public void move(){

        int xy[] = new int[2];
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        xy[0] = (int) b.getX();
        xy[1] = (int) b.getY();



       mf.textField1.setText("x: " + xy[0] + "\ty: " + xy[1]);
    }




}
