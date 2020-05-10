import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JButton continueButton;

    private BufferedImage background;
    private BufferedImage logomini;
    private BufferedImage lock;
    private BufferedImage hide;
    private BufferedImage close;


    Confirmation(){

        try {
            background = ImageIO.read(new File("res/img/background.png"));
            logomini = ImageIO.read(new File("res/img/logo-mini.png"));
            lock = ImageIO.read(new File("res/img/icon-lock.png"));
            hide = ImageIO.read(new File("res/img/icon-hide.png"));
            close = ImageIO.read(new File("res/img/icon-close.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        passwordField1.setEchoChar('?');

        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (passwordField1.getPassword().length!=0) {continueButton.setEnabled(true);}
            }
        });
    }

    public String getPField () {
        String code = "";
        for (char c : passwordField1.getPassword()) {
            code += c;
        }
        return code;
    }

    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public void setPhoneNumber(String number) {
        phoneNumber.setText(number);
    }

    public void setButton(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        bottomPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        logo = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logomini, 0, 0, null);
            }
        };
        passwordField1 = new JPasswordField(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                passwordField1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                g.setColor(Color.WHITE);
                g.drawRect(0, 38, 100, 2);
                g.drawImage(lock, 0, 0, null);
            }
        };
        rollupButton = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(hide, 1, 1, null);
            }
        };
        closeButton = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(close, 1, 1, null);
            }
        };
    }
}
