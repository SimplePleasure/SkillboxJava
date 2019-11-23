package com.myapp.forms;

import com.myapp.Contact;

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

public class ContactAddPane extends JPanel {
    private Contact contact;
    private JTextPane infoTextFirst;
    private JTextPane infoTextSecond;
    private JPanel numberPane;
    private JPanel phoneIcon;
    private JTextField countryCode;
    private JTextField number;
    private JTextField firstName;
    private JTextField lastName;
    private JLabel confirmButton;
    private JPanel bottomPane;
    private JLabel backButton;

    public ContactAddPane(MainFrame mainFrame) {
        this.contact = contact;

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
                80
        )));

        infoTextFirst = new JTextPane();
        infoTextFirst.setOpaque(false);
        StyledDocument docFirst = infoTextFirst.getStyledDocument();
        SimpleAttributeSet centerFirst = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerFirst, StyleConstants.ALIGN_CENTER);
        docFirst.setParagraphAttributes(0, docFirst.getLength(), centerFirst, false);
        infoTextFirst.setEditable(false);
        infoTextFirst.setAlignmentX(CENTER_ALIGNMENT);
        infoTextFirst.setText("Добавление контакта");
        infoTextFirst.setMaximumSize(new Dimension(
                Short.MAX_VALUE,
                Short.MAX_VALUE
        ));
        try {
            infoTextFirst.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 42));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoTextFirst.setForeground(new Color(
                0,
                181,
                226
        ));
        add(infoTextFirst);
        add(Box.createRigidArea(new Dimension(
                0,
                30
        )));

        infoTextSecond = new JTextPane();
        infoTextSecond.setOpaque(false);
        StyledDocument docSecond = infoTextSecond.getStyledDocument();
        SimpleAttributeSet centerSecond = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerSecond, StyleConstants.ALIGN_CENTER);
        docSecond.setParagraphAttributes(0, docSecond.getLength(), centerSecond, false);
        infoTextSecond.setEditable(false);
        infoTextSecond.setAlignmentX(CENTER_ALIGNMENT);
        infoTextSecond.setText("Введите код страны и номер мобильного телефона пользователя");
        infoTextSecond.setMaximumSize(new Dimension(
                300,
                Short.MAX_VALUE
        ));
        try {
            infoTextSecond.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoTextSecond.setForeground(Color.WHITE);
        add(infoTextSecond);

        numberPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        numberPane.setAlignmentX(CENTER_ALIGNMENT);
        numberPane.setPreferredSize(new Dimension(
                400,
                55
        ));
        numberPane.setMaximumSize(new Dimension(
                400,
                55
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
                    if (getLength()< 10) {
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
                15
        )));

        firstName = new JTextField();
        firstName.setOpaque(false);
        firstName.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));
        firstName.setPreferredSize(new Dimension(
                400,
                45
        ));
        firstName.setMaximumSize(new Dimension(
                400,
                45
        ));
        firstName.setHorizontalAlignment(JTextField.CENTER);
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
                15
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
        lastName.setPreferredSize(new Dimension(
                400,
                45
        ));
        lastName.setMaximumSize(new Dimension(
                400,
                45
        ));
        lastName.setHorizontalAlignment(JTextField.CENTER);
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
        confirmButton.setText("ДОБАВИТЬ");
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
                30
        )));

        bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));
        bottomPane.setOpaque(false);
        add(bottomPane);
        backButton = new JLabel();
        backButton.setIcon(new ImageIcon("resources/icon-back.png"));
        bottomPane.add(backButton);
        bottomPane.add(Box.createRigidArea(new Dimension(
                755,
                0
        )));
        add(Box.createRigidArea(new Dimension(
                0,
                30
        )));
    }

    public JTextField getNumber() {
        return number;
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

    public JLabel getBackButton() {
        return backButton;
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

