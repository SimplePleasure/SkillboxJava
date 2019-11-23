package com.myapp.forms;

import com.myapp.Contact;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ContactEditPane extends JPanel {
    private Contact contact;
    private JTextPane infoText;
    private JPanel overlayPane;
    private JPanel icon;
    private JTextField nameText;
    private JPanel deletePane;
    private JTextField phoneNumberText;
    private JLabel deleteButton;
    private JLabel confirmButton;
    private JPanel bottomPane;
    private JLabel backButton;

    public ContactEditPane(Contact contact) {
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
                110
        )));

        infoText = new JTextPane();
        infoText.setOpaque(false);
        StyledDocument doc = infoText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        infoText.setEditable(false);
        infoText.setAlignmentX(CENTER_ALIGNMENT);
        infoText.setText("Редактирование контакта");
        infoText.setMaximumSize(new Dimension(
                Short.MAX_VALUE,
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

        overlayPane = new JPanel();
        overlayPane.setOpaque(false);
        overlayPane.setLayout(new OverlayLayout(overlayPane));
        add(overlayPane);

        icon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                Ellipse2D el = new Ellipse2D.Double(10,0,65, 65);
                g2.setClip(el);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/background.png"
                    ));

                    g2.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g2.setColor(Color.WHITE);
            }
        };
        icon.setOpaque(false);
        icon.setMaximumSize(new Dimension(
                400,
                70
        ));
        overlayPane.add(icon);

        nameText = new JTextField();
        nameText.setText(contact.getNameText());
        nameText.setOpaque(false);
        nameText.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 2, 0, Color.WHITE),
                new EmptyBorder(10, 85, 0, 0)

        ));
        try {
            nameText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpensansLight.ttf")))
                    .deriveFont(Font.PLAIN, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameText.setForeground(Color.WHITE);
        nameText.setCaretColor(Color.WHITE);
        nameText.setAlignmentX(CENTER_ALIGNMENT);
        nameText.setMaximumSize(new Dimension(
                400,
                60
        ));
        overlayPane.add(nameText);
        add(Box.createRigidArea(new Dimension(
                0,
                20
        )));

        deletePane = new JPanel();
        deletePane.setOpaque(false);
        deletePane.setPreferredSize(new Dimension(

        ));
        add(deletePane);

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
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        phoneNumberText.setText(contact.getPhone());
        phoneNumberText.setForeground(Color.WHITE);
        deletePane.add(phoneNumberText);

        deleteButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                super.paintComponent(g2);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/icon-trash.png"
                    ));
                    g2.drawImage(icon, 10, 7, 15, 18, this);
                    try {
                        g2.setFont(Font.createFont(
                                Font.TRUETYPE_FONT,
                                new FileInputStream(new File(
                                        "resources/OpenSansRegular.ttf")))
                                .deriveFont(Font.PLAIN, 16));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    g2.setColor(new Color(
                            247,
                            74,
                            79
                    ));
                    g2.drawString("удалить пользователя", 34, 22);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        deleteButton.setOpaque(false);
        deleteButton.setPreferredSize(new Dimension(
                220,
                33
        ));
        deleteButton.setMaximumSize(new Dimension(
                220,
                33
        ));
        deleteButton.setBorder(BorderFactory.createMatteBorder(
                2,
                2,
                2,
                2,
                new Color(
                        247,
                        74,
                        79
                )
        ));
        deletePane.add(deleteButton);
        add(Box.createRigidArea(new Dimension(
                0,
                60
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
                90
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

    public JTextField getNameText() {
        return nameText;
    }

    public JLabel getDeleteButton() {
        return deleteButton;
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

