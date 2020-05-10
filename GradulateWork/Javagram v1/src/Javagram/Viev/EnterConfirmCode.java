package Javagram.Viev;

import Javagram.AppSettings.Config;
import Javagram.Storage.ABSUserData;
import Javagram.Storage.UserData;
import Javagram.TgAPI.TelegramAPI;
import Javagram.interfaces.MainInterface;
import org.javagram.response.AuthAuthorization;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class EnterConfirmCode implements MainInterface.IConfirmPhone {
    private JPanel rootPanel;
    private JPanel logoPanel;
    private JLabel phone;
    private JTextPane prompt;
    private JPasswordField passwordField;
    private JPanel enterCodePanel;
    private JButton next;
    private JPanel lockPanel;

    public EnterConfirmCode() {
        initPanel();
        WindowHandler.setContainer(this);


        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (passwordField.getPassword().length == 5) {
                    next.setEnabled(true);
                } else {
                    next.setEnabled(false);
                }
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                char[] pass = passwordField.getPassword();
                StringBuilder code = new StringBuilder();
                for (char c : pass) {
                    code.append(c);
                }
                sentConfirmCode(code.toString());
            }
        });
    }

    void initPanel() {

        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        phone.setText("+" + ABSUserData.getStorage().getPhone());
        passwordField.setBorder(BorderFactory.createEmptyBorder());

        StyledDocument doc = prompt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    @Override
    public void sentConfirmCode(String code) {
        try {
            boolean authorizationComplete = TelegramAPI.sentConfirmCode(code);
            if (authorizationComplete) {
                new FinishRegister();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Config.background, 0, 0, null);
            }
        };

        logoPanel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                g.drawImage(Config.logoMini, 0, 50, null);
            }
        };

        enterCodePanel = new JPanel() {
            @Override
            public void paintComponent (Graphics g) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 57, 150, 59);
                g.drawImage(Config.lock, 0, 20, null);
            }
        };
    }

}
