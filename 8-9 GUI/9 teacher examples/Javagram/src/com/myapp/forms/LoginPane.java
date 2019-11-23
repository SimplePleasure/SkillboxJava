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

public class LoginPane extends JPanel {
    private JPanel logoPane;
    private JTextPane infoText;
    private JPanel numberPane;
    private JPanel phoneIcon;
    private JTextField countryCode;
    private JTextField number;
    private JLabel confirmButton;

    public LoginPane() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBounds(0, 0, 900, 597);
        add(Box.createRigidArea(new Dimension(
                0,
                50
        )));

        logoPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/logo.png"
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
                165,
                168
        ));
        logoPane.setMaximumSize(new Dimension(
                165,
                168
        ));
        add(logoPane);
        add(Box.createRigidArea(new Dimension(
                0,
                55
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
                    .deriveFont(Font.PLAIN, 18));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoText.setOpaque(false);
        infoText.setForeground(Color.WHITE);
        infoText.setEditable(false);
        infoText.setAlignmentX(CENTER_ALIGNMENT);
        infoText.setText("Введите код страны и номер вашего мобильного телефона");
        infoText.setMaximumSize(new Dimension(
                300,
                Short.MAX_VALUE
        ));
        add(infoText);
//        add(Box.createRigidArea(new Dimension(
//                0,
//                10
//        )));

        numberPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 7));
        numberPane.setAlignmentX(CENTER_ALIGNMENT);
        numberPane.setPreferredSize(new Dimension(
                400,
                60
        ));
        numberPane.setMaximumSize(new Dimension(
                400,
                60
        ));
        numberPane.setOpaque(false);
        numberPane.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));

        phoneIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/icon-phone.png"
                    ));
                    g.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.paintComponent(g);
            }
        };
        phoneIcon.setOpaque(false);
        phoneIcon.setPreferredSize(new Dimension(
                22,
                36
        ));
        phoneIcon.setMaximumSize(new Dimension(
                22,
                36
        ));
        numberPane.add(phoneIcon);
        countryCode = new JTextField();
        countryCode.setText("+7");
        countryCode.setEditable(false);
        countryCode.setOpaque(false);
        countryCode.setBorder(null);
        try {
            countryCode.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }
        countryCode.setForeground(Color.WHITE);
        numberPane.add(countryCode);

        number = new JTextField();
        number.setDocument(new PlainDocument() {
            String chars = "0123456789";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    if (getLength() < 10) {
                        super.insertString(offs, str, a);
                    }
                }
            }
        });
        number.setOpaque(false);
        number.setBorder(null);
        number.setPreferredSize(new Dimension(
                240,
                40
        ));
        number.setMaximumSize(new Dimension(
                240,
                40
        ));
        try {
            number.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }
        number.setForeground(Color.WHITE);
        number.setCaretColor(Color.WHITE);
        numberPane.add(number);
        add(numberPane);
        add(Box.createRigidArea(new Dimension(
                0,
                45
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
        confirmButton.setText("ПРОДОЛЖИТЬ");
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

    public JTextField getNumber() {
        return number;
    }

    public JLabel getConfirmButton() {
        return confirmButton;
    }

    public JTextPane getInfoText() {
        return infoText;
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
