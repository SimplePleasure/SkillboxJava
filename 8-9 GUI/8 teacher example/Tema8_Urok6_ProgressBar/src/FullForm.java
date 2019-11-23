import javax.swing.*;
import java.awt.event.ActionListener;

public class FullForm extends JPanel{
    private JPanel rootpanel;
    private JLabel surName;
    private JTextField textSurname;
    private JTextField textName;
    private JTextField textFatherName;
    private JLabel fatherName;
    private JButton button;
    private JLabel labelname;

    public JPanel getRootpanel() {
        return rootpanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootpanel=this;
    }
    public Person getPerson(){
        return new Person(textSurname.getText().trim(),textName.getText().trim(),textFatherName.getText().trim());
    }
    public void setPerson(Person person){
        textSurname.setText(person.getSurname().trim());
        textName.setText(person.getName().trim());
        textFatherName.setText(person.getFathername().trim());
    }
    public void addApplyPerson(ActionListener  listener){
        button.addActionListener(listener);
    }
    public void removeApplyPerson(ActionListener listener){
        button.removeActionListener(listener);
    }
}
