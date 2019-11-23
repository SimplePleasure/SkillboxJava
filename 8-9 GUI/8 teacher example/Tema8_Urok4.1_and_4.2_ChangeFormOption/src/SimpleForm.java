import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleForm extends JPanel{
    private JPanel rootPanel;
    private JLabel labelFio;
    private JTextField textFIO;
    private JButton button;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
    public Person getPerson(){
        String fio[]=textFIO.getText().trim().split("\\s++");
        switch (fio.length){
            case 0: return new Person();
            case 1: return new Person(fio[0],"","");
            case 2: return new Person(fio[0],fio[1],"");
            case 3: return new Person(fio[0],fio[1],fio[2]);
            default: return null;
        }

    }
    public void setPerson(Person person){
        textFIO.setText(person.toString());
    }
    public void addApplyPerson(ActionListener listener){
        button.addActionListener(listener);
    }
    public void removeApplyPerson(ActionListener listener){
        button.removeActionListener(listener);
    }
}
