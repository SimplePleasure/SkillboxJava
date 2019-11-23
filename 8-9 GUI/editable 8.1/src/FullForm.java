import javax.swing.*;
import java.awt.event.ActionListener;

public class FullForm //extends JPanel
{
    private JPanel RootPanel;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField fathernameField;
    private JLabel Surname;
    private JLabel Name;
    private JLabel Fathername;
    private JButton Ok;

    public String getSurname(){
        return surnameField.getText();
    }
    public String getName(){
        return nameField.getText();
    }
    public String getFatherName(){
        return fathernameField.getText();
    }
    public void setData(String surname, String name, String fathername) {
        surnameField.setText(surname);
        nameField.setText(name);
        fathernameField.setText(fathername);
    }
    public int sout() {
        Person p = new Person(Surname.getText(), Name.getText(), Fathername.getText());
        return p.getCount();
    }




    public JPanel getRootPanel() {
        return RootPanel;
    }
    public void setButton(ActionListener listener) {
        Ok.addActionListener(listener);
    }
}
