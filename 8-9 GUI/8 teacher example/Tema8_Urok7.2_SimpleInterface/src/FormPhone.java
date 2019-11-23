import javax.swing.*;
import java.awt.event.ActionListener;

public class FormPhone extends JPanel{
    private JPanel rootPanel;
    private JLabel label1;
    private JTextField textNomer;
    private JButton Button;



    public JPanel getRootPanel() {
        return rootPanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
    public void addApplyAction(ActionListener  listener){
        Button.addActionListener(listener);
    }

}
