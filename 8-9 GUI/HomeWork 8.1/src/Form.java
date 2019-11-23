import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Form {
    private JPanel RootPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextField surnameField;
    private JLabel surname;
    private JLabel name;
    private JTextField nameField;
    private JLabel patronymic;
    private JTextField patronymicField;
    private JLabel birthday;
    private JTextField birthdayField;
    private JTextField cityField;
    private JLabel city;
    private JButton button;


    public Form() {
        RootPanel.setLayout( new BoxLayout(RootPanel, BoxLayout.X_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));




    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
