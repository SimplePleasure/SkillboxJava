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

public class CodeConfirmPane extends JPanel {
    private JPanel logoPane;
    private JTextField numberText;
    private JTextPane infoText;
    private JPanel codePane;
    private JPanel codeIcon;
    private JPasswordField codeText;
    private JLabel confirmButton;

    public CodeConfirmPane() {
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
                60
        )));

        numberText = new JTextField();
        numberText.setText("123456");
        numberText.setHorizontalAlignment(JTextField.CENTER);
        numberText.setOpaque(false);
        numberText.setBorder(null);
        numberText.setPreferredSize(new Dimension(
                330,
                40
        ));
        numberText.setMaximumSize(new Dimension(
                330,
                40
        ));
        try {
            numberText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansLight.ttf")))
                    .deriveFont(Font.PLAIN, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }
        numberText.setForeground(Color.WHITE);
        add(numberText);
        add(Box.createRigidArea(new Dimension(
                0,
                20
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
        infoText.setText("На данный номер телефона было отправлено" +
                " SMS-сообщение с кодом подтверждения." +
                "Пожалуйста, введите этот код в поле ниже:");
        infoText.setMaximumSize(new Dimension(
                400,
                Short.MAX_VALUE
        ));
        add(infoText);

        codePane = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 3));
        codePane.setAlignmentX(CENTER_ALIGNMENT);
        codePane.setPreferredSize(new Dimension(
                160,
                50
        ));
        codePane.setMaximumSize(new Dimension(
                160,
                50
        ));
        codePane.setOpaque(false);
        codePane.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                2,
                0,
                Color.WHITE
        ));

        codeIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/icon-lock.png"
                    ));
                    g.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.paintComponent(g);
            }
        };
        codeIcon.setOpaque(false);
        codeIcon.setPreferredSize(new Dimension(
                26,
                36
        ));
        codeIcon.setMaximumSize(new Dimension(
                26,
                36
        ));
        codePane.add(codeIcon);

        codeText = new JPasswordField();
        codeText.setDocument(new PlainDocument() {
            String chars = "0123456789";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    if (getLength()< 5) {
                        super.insertString(offs, str, a);
                    }
                }
            }
        });
        codeText.setOpaque(false);
        codeText.setBorder(null);
        codeText.setPreferredSize(new Dimension(
                105,
                40
        ));
        codeText.setMaximumSize(new Dimension(
                105,
                40
        ));
        codeText.setFont(new Font(
                "Arial",
                Font.PLAIN,
                34
        ));
        codeText.setForeground(Color.WHITE);
        codeText.setCaretColor(Color.WHITE);
        codePane.add(codeText);
        add(codePane);
        add(Box.createRigidArea(new Dimension(
                0,
                45
        )));

        ImageIcon icon = new ImageIcon("resources/button-background.png");
        confirmButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), -2, 0, null);
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

    public JTextField getNumberText() {
        return numberText;
    }

    public JPasswordField getCodeText() {
        return codeText;
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
