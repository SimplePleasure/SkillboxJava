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

public class UserEditPane extends JPanel {
    private JTextPane infoText;
    private JTextField firstName;
    private JTextField lastName;
    private JLabel confirmButton;
    private JPanel bottomPane;
    private JLabel backButton;
    private JTextField phoneNumberText;
    private JTextField logoutButton;

    public UserEditPane() {
        setOpaque(false);
        setBounds(0, 0, 900, 597);
        setBackground(new Color(
                0,
                0,
                0
        ));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(
                0,
                60
        )));

        infoText = new JTextPane();
        infoText.setOpaque(false);
        StyledDocument doc = infoText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        infoText.setEditable(false);
        infoText.setAlignmentX(CENTER_ALIGNMENT);
        infoText.setText("Настройки профиля");
        infoText.setMaximumSize(new Dimension(
                500,
                Short.MAX_VALUE
        ));
        try {
            infoText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 42));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoText.setForeground(new Color(
                0,
                181,
                226
        ));
        add(infoText);
//        add(Box.createRigidArea(new Dimension(
//                0,
//                50
//        )));

        firstName = new JTextField();
        firstName.setOpaque(false);
        firstName.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));
        try {
            firstName.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpensansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
        firstName.setForeground(Color.WHITE);
        firstName.setCaretColor(Color.WHITE);
        firstName.setAlignmentX(CENTER_ALIGNMENT);
        firstName.setMaximumSize(new Dimension(
                200,
                28
        ));
        add(firstName);
        add(Box.createRigidArea(new Dimension(
                0,
                20
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
        try {
            lastName.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpensansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastName.setForeground(Color.WHITE);
        lastName.setCaretColor(Color.WHITE);
        lastName.setAlignmentX(CENTER_ALIGNMENT);
        lastName.setMaximumSize(new Dimension(
                200,
                28
        ));
        add(lastName);
        add(Box.createRigidArea(new Dimension(
            0,
            115
        )));

        confirmButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/button-background.png"
                    ));
                    g.drawImage(icon, -2, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        confirmButton.setText("СОХРАНИТЬ");
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
                80
        )));

        bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));
        bottomPane.setOpaque(false);
        add(bottomPane);
        backButton = new JLabel();
        backButton.setIcon(new ImageIcon("resources/icon-back.png"));
        bottomPane.add(backButton);
        bottomPane.add(Box.createRigidArea(new Dimension(
                600,
                0
        )));

        phoneNumberText = new JTextField();
        phoneNumberText.setOpaque(false);
        phoneNumberText.setBorder(null);
        phoneNumberText.setPreferredSize(new Dimension(
                150,
                24
        ));
        phoneNumberText.setMaximumSize(new Dimension(
                150,
                24

        ));

        try {
            phoneNumberText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 20));
        } catch (Exception e) {
            e.printStackTrace();
        }
        phoneNumberText.setText("+79313547550");
        phoneNumberText.setForeground(Color.WHITE);
        bottomPane.add(phoneNumberText);

        logoutButton = new JTextField();
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logoutButton.setForeground(Color.WHITE);
                logoutButton.setBorder(BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        Color.WHITE
                ));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                logoutButton.setForeground(new Color(
                        0,
                        181,
                        226
                ));
                logoutButton.setBorder(BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        new Color(
                                0,
                                181,
                                226
                        )
                ));

            }
        });
        logoutButton.setOpaque(false);
        logoutButton.setHorizontalAlignment(JTextField.CENTER);
        logoutButton.setPreferredSize(new Dimension(
                60,
                20
        ));
        logoutButton.setMaximumSize(new Dimension(
                60,
                20
        ));
        logoutButton.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                1,
                0,
                new Color(
                        0,
                        181,
                        226
                )
        ));
        logoutButton.setText("ВЫЙТИ");
        try {
            logoutButton.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logoutButton.setForeground(new Color(
                0,
                181,
                226
        ));
        bottomPane.add(logoutButton);

        add(Box.createRigidArea(new Dimension(
                0,
                30
        )));
    }

    public JTextPane getInfoText() {
        return infoText;
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

    public JPanel getBottomPane() {
        return bottomPane;
    }

    public JLabel getBackButton() {
        return backButton;
    }

    public JTextField getPhoneNumberText() {
        return phoneNumberText;
    }

    public JTextField getLogoutButton() {
        return logoutButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2d.setColor(getBackground());
        g2d.fill(getBounds());
        g2d.dispose();
    }
}
