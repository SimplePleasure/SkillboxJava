import javax.swing.*;

public class Form extends JPanel{
    private JPanel rootPanel;
    private JLabel labelSurname;
    private JLabel labelName;
    private JLabel labelfatherName;
    private JLabel labelBirthday;
    private JLabel labelAdress;
    private JTextField textSurname;
    private JTextField textName;
    private JTextField textFatherName;
    private JTextField textBirthday;
    private JTextField textAdress;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel=this;
    }
}
