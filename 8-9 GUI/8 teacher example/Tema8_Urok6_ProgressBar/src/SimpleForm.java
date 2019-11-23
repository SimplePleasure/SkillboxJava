import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

public class SimpleForm extends JPanel{
    private JPanel rootPanel;
    private JLabel labelFio;
    private JTextField textFIO;
    private JButton button;
    private JProgressBar progressBar;
    {
        textFIO.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                progresUpdate();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                progresUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
    public Person getPerson(){
        String fio[]=getTokens();
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
    protected String[] getTokens(){
        String str=textFIO.getText().trim();
        if (str.isEmpty()) return new String[0];
        else return str.split("\\s++");
    }
    protected void progresUpdate(){

        progressBar.setValue(getTokens().length);
    }
}
