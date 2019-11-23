import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Confirmation {

    private JPanel rootPanel;
    private JButton rollupButton;
    private JButton closeButton;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel logo;
    private JLabel phoneNumber;
    private JTextPane message;
    private JLabel Confirmation;
    private JPasswordField passwordField1;
    private JButton Continue;


    Confirmation(){



        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        passwordField1.setEchoChar('♦');
        passwordField1.setToolTipText("Чтобы увидеть код нажмите ctrl");

        rollupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.confirm.setState(JFrame.ICONIFIED);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.confirm.setVisible(false);
                System.exit(0);
            }
        });



        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                passwordField1.setEchoChar('♦');

                passwordField1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if (e.isControlDown()) {
                            passwordField1.setEchoChar((char)0);
                        }
                    }
                });
                if (passwordField1.getPassword().length == 4) {
                    Continue.setEnabled(true);
                }else {Continue.setEnabled(false);}
            }
        });


        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Loader.confirm.setVisible(false);
                    Loader.getNameframe.setVisible(true);
            }
        });
    }




    public void getNumber(String number){
        phoneNumber.setText(number);
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
