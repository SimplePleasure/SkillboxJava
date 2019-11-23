import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyForm extends JPanel
{
    protected JTextField textField1;
    private JButton button1;
    private JPanel RootPanel;
    protected JButton moveButton;




    public void setButton(ActionListener listener) {
        button1.addActionListener(listener);
    }


    public void getMove(ActionListener listener) {
        moveButton.addActionListener(listener);
    }







    public JPanel getRootPanel() {
        return RootPanel;
    }
}

