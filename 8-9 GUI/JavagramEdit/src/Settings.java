import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Settings {

    private JPanel RootPanel;
    private JButton closeButton;
    private JButton rollUpButton;
    private JPanel CenterPanel;
    private JPanel MainPanel;
    private JPanel PhotoSettings;
    private JPanel NameSettings;
    private JPanel TopPanel;
    private JButton Ok;
    private JPanel BottomPanel;
    private JButton editImage;
    private JButton deleteImage;
    private JTextField name;
    private JTextField surname;
    private JButton userExit;
    private JLabel phoneNumber;
    private JButton back;





    public Settings() {

        CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.Y_AXIS));
        PhotoSettings.setLayout(new BoxLayout(PhotoSettings, BoxLayout.Y_AXIS));
        NameSettings.setLayout(new BoxLayout(NameSettings, BoxLayout.Y_AXIS));
        Ok.setEnabled(false);

        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (name.getText().trim().length() == 0 || surname.getText().trim().length() == 0) {
                    Ok.setEnabled(false);
                } else {
                    Ok.setEnabled(true);
                }
            }
        });
        surname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (name.getText().trim().length() == 0 || surname.getText().trim().length() == 0) {
                    Ok.setEnabled(false);
                } else {
                    Ok.setEnabled(true);
                }
            }
        });
    }






    public void setSaveButton(ActionListener listener) {
        Ok.addActionListener(listener);
    }
    public void setBackButton(ActionListener listener) {
        back.addActionListener(listener);
    }
    public void setExitButton(ActionListener listener) {
        userExit.addActionListener(listener);
    }
    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }
    public String getEditName() {
        String userName = this.name.getText() + " " + this.surname.getText();
        return userName;
    }
    public void setname(String username) {
        String name [] = username.split("\\s+");
        this.name.setText(name[0]);
        this.surname.setText(name[1]);
    }
    public void setPhoneNumber(String number) {
        phoneNumber.setText(number);
    }
    public JPanel getRootPanel() {
        return RootPanel;
    }
}
