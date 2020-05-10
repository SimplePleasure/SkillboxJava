package Javagram.Viev;

import Javagram.AppSettings.Config;
import Javagram.Storage.ABSUserData;
import Javagram.TgAPI.TelegramAPI;
import Javagram.interfaces.MainInterface;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class FinishRegister implements MainInterface.IRegister {

    private JPanel rootPanel;
    private JTextPane prompt;
    private JTextField firstName;
    private JTextField lastName;
    private JPanel labelPanel;
    private JPanel lastNamePanel;
    private JPanel firstNamePanel;
    private JButton finish;
    private JTextField test;

    public FinishRegister() {
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        WindowHandler.setContainer(this);
        initPanel();

        firstName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firstName.setText("");
            }
        });
        lastName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastName.setText("");
            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                finishRegister(firstName.getText(), lastName.getText());
            }
        });
    }

    void initPanel() {

        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));


        firstName.setBorder(BorderFactory.createEmptyBorder());
        firstName.setHorizontalAlignment(0);

        lastName.setBorder(BorderFactory.createEmptyBorder());
        lastName.setHorizontalAlignment(0);
        String firstN = ABSUserData.getStorage().getFirstName();
        String lastN = ABSUserData.getStorage().getLastName();
        if (firstN != null && firstN.length() != 0) {
            firstName.setText(firstN);
        }
        if (lastN != null && lastN.length() != 0) {
            lastName.setText(lastN);
        }


        //Выравнивание JTextPane по центру OX
        StyledDocument doc = prompt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    @Override
    public void finishRegister(String firstName, String lastName) {
        try {
            boolean complete = TelegramAPI.fillInitials(firstName, lastName);
            if (complete) {
                new MainScreen();
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
                g.drawImage(Config.background, 0, 0, null);
            }
        };

        labelPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Config.logoMini, 0, 50, null);
            }
        };

        firstNamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 33, 220, 35);
            }
        };

        lastNamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(0, 33, 220, 35);
            }
        };
    }

}
