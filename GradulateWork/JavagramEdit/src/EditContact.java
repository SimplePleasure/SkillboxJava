import javax.swing.*;
import java.awt.event.ActionListener;

public class EditContact {
    private JPanel RootPanel;
    private JTextField ContactName;
    private JPanel DelPanel;
    private JLabel ContactPhone;
    private JButton Del;
    private JButton save;
    private JPanel BackPanel;
    private JButton BackButton;

    {
        RootPanel.setLayout(new BoxLayout(RootPanel, BoxLayout.Y_AXIS));
        DelPanel.setLayout(new BoxLayout(DelPanel, BoxLayout.X_AXIS));
    }





    public String getEditContactName(){
        return ContactName.getText();
    }

    public void setDelButton(ActionListener listener) {
        Del.addActionListener(listener);
    }
    public void setSaveButton(ActionListener listener) {
        save.addActionListener(listener);
    }
    public void setBackButton(ActionListener listener) {
        BackButton.addActionListener(listener);
    }

    public void setContactName(String name, String phone) {
        ContactName.setText(name);
        ContactPhone.setText(phone);
    }
    public JPanel getRootPanel() {
        return RootPanel;
    }
}
