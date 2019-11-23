import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddContact {
    private JPanel RootPanel;
    private JPanel TopPanel;
    private JPanel MainPanel;
    private JPanel BottomPanel;
    private JButton CloseButton;
    private JButton RollUpButton;
    private JButton Back;
    private JPanel SearshNumberPanel;
    private JTextField SearshUserPhone;

    private JButton Add;
    private JLabel userName;


    {
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        Add.setEnabled(false);
    }

    public AddContact() {
        Users user = new Users();
        SearshUserPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (SearshUserPhone.getText().length()>0) {
                    Add.setEnabled(true);
                } else {
                    Add.setEnabled(false);
                }

            }
        });
//        SearshUserPhone.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
//                String name = user.searsh(SearshUserPhone.getText());
//                if ( user.searsh(SearshUserPhone.getText()) != null ) {
//                    userName.setText( name );
//                } else {
//                    userName.setText("");
//                }
//            }
//        });
    }

    protected String getPhone() {
        return SearshUserPhone.getText();
    }
    protected String getName(){
        return userName.getText();
    }

    public void setAddButton(ActionListener listener) {
        Add.addActionListener(listener);
    }
    public void setBackButton(ActionListener listener) {
        Back.addActionListener(listener);
    }
    public JPanel getRootPanel() {
        return RootPanel;
    }
}










