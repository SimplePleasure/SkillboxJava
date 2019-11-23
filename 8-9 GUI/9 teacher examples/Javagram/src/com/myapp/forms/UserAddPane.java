package com.myapp.forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UserAddPane extends JPanel {
    private JPanel logoPane;
    private JTextPane infoText;
    private JTextField firstName;
    private JTextField lastName;
    private JLabel confirmButton;

    public UserAddPane() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBounds(0, 0, 900, 597);
        add(Box.createRigidArea(new Dimension(
                0,
                35
        )));

        logoPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/logo-mini.png"
                    ));
                    g.drawImage(icon, 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.paintComponent(g);
            }
        };
        logoPane.setOpaque(false);
        logoPane.setAlignmentX(CENTER_ALIGNMENT);
        logoPane.setPreferredSize(new Dimension(
                124,
                98
        ));
        logoPane.setMaximumSize(new Dimension(
                124,
                98
        ));
        add(logoPane);
        add(Box.createRigidArea(new Dimension(
                0,
                40
        )));

        infoText = new JTextPane();
        StyledDocument doc = infoText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        try {
            infoText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoText.setOpaque(false);
        infoText.setForeground(Color.WHITE);
        infoText.setEditable(false);
        infoText.setAlignmentX(CENTER_ALIGNMENT);
        infoText.setText("Введите ваши имя и фамилию для завершения регистрации");
        infoText.setMaximumSize(new Dimension(
                250,
                Short.MAX_VALUE
        ));
        add(infoText);

        firstName = new JTextField();
        firstName.setOpaque(false);
        firstName.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));
        firstName.setText("Имя");
        firstName.setPreferredSize(new Dimension(
                240,
                40
        ));
        firstName.setMaximumSize(new Dimension(
                240,
                40
        ));
        try {
            firstName.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
        firstName.setForeground(Color.WHITE);
        firstName.setCaretColor(Color.WHITE);
        add(firstName);
        add(Box.createRigidArea(new Dimension(
                0,
                25
        )));

        lastName = new JTextField();
        lastName.setOpaque(false);
        lastName.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));
        lastName.setText("Фамилия");
        lastName.setPreferredSize(new Dimension(
                240,
                40
        ));
        lastName.setMaximumSize(new Dimension(
                240,
                40
        ));
        try {
            lastName.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastName.setForeground(Color.WHITE);
        lastName.setCaretColor(Color.WHITE);
        add(lastName);
        add(Box.createRigidArea(new Dimension(
                0,
                65
        )));

        ImageIcon icon = new ImageIcon("resources/button-background.png");
        confirmButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), -1, 0, null);
                super.paintComponent(g);
            }
        };
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                confirmButton.setBorder(BorderFactory.createEtchedBorder(1));
                super.mouseClicked(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                confirmButton.setBorder(null);
                super.mouseReleased(e);
            }
        });
        confirmButton.setText("ЗАВЕРШИТЬ");
        try {
            confirmButton.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.BOLD, 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setHorizontalAlignment(JLabel.CENTER);
        confirmButton.setVerticalAlignment(JLabel.CENTER);
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        confirmButton.setPreferredSize(new Dimension(
                334,
                59
        ));
        confirmButton.setMaximumSize(new Dimension(
                334,
                59
        ));
        add(confirmButton);
        add(Box.createRigidArea(new Dimension(
                0,
                100
        )));
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JLabel getConfirmButton() {
        return confirmButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            BufferedImage icon = ImageIO.read(new File(
                    "resources/background.png"
            ));
            g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
    }
}

