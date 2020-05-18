package Javagram.Viev;

import Javagram.AppSettings.Config;
import Javagram.TgAPI.TelegramAPI;
import Javagram.interfaces.MainInterface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;

public class EnterPhoneNum implements MainInterface.IEnterPhoneNum {
    private JPanel rootPanel;
    private JPanel logoPanel;
    private JFormattedTextField phoneField;
    private JButton next;
    private JTextArea prompt;
    private JPanel phoneImgPanel;
    private JPanel phoneEnterPanel;
    private MaskFormatter maskFormatter;


    public EnterPhoneNum() {
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        setPhoneFormatter();
        WindowHandler.setContainer(this);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String clearedPhone = maskFormatter.stringToValue(phoneField.getText()).toString();
                    checkPhone(clearedPhone);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (next.getText().equals("error")) {
                    next.setText("ПРОДОЛЖИТЬ");
                }
                if (phoneField.getText().replaceAll("[^0-9]", "").length() == 11) {
                    next.setEnabled(true);
                } else {
                    next.setEnabled(false);
                }
            }
        });
    }

    public void setPhoneFormatter() {

        phoneField.setBorder(BorderFactory.createEmptyBorder());
        phoneField.setCaretColor(new Color(0, 181, 232, 255));
        phoneField.setHorizontalAlignment(0);
        try {
            maskFormatter = new MaskFormatter(Config.phoneMask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            maskFormatter.setPlaceholder("7");
            maskFormatter.install(phoneField);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void checkPhone(String phone) {
        try {
            boolean isRegistred = TelegramAPI.isPhoneRegistred(phone);
            if (isRegistred) {
                new EnterConfirmCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
            next.setText("error");
        }
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
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Config.logo, 0, 0, null);
            }
        };


        phoneEnterPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 43, 245, 45);
            }
        };
        phoneImgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Config.phone, 0, 0, null);
            }
        };


    }
}
