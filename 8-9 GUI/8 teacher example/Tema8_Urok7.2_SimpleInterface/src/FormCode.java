import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCode extends JPanel{
    private JPanel rootPanel;
    private JLabel labelCode;
    private JTextField textCode;
    private JButton button;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
    public void addApplyAction(ActionListener  listener){
        button.addActionListener(listener);
    }


}
