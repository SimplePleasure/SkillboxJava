package com.myapp.forms;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class ContactPane extends JPanel implements ListCellRenderer<Object> {
    private JPanel iconPane;
    private LayoutManager overlay;
    private JLabel userIcon;
    private JLabel userIconMask;
    private JPanel userInfoPane;
    private JTextField userNameText;
    private JTextField lastMessageText;
    private JTextField lastSeenDateText;

    public ContactPane() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        setBackground(new Color(
                236,
                236,
                236
        ));
        setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                1,
                0,
                new Color(
                        210,
                        210,
                        210
                )));
        setMaximumSize(new Dimension(
                250,
                65
        ));

        iconPane = new JPanel();
        overlay = new OverlayLayout(iconPane);
        iconPane.setLayout(overlay);
        iconPane.setPreferredSize(new Dimension(
                41,
                41
        ));
        add(iconPane);
        userIconMask = new JLabel();
        iconPane.add(userIconMask);
        userIcon = new JLabel();
        iconPane.add(userIcon);

        userInfoPane = new JPanel();
        userInfoPane.setOpaque(false);
        userInfoPane.setLayout(new BoxLayout(userInfoPane, BoxLayout.Y_AXIS));
        userInfoPane.add(Box.createRigidArea(new Dimension(
                0,
                15

        )));

        userInfoPane.setPreferredSize(new Dimension(
                160,
                65
        ));
        userInfoPane.setMaximumSize(new Dimension(
                160,
                65
        ));
        add(userInfoPane);

        userNameText = new JTextField();
        userNameText.setOpaque(false);
        userNameText.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        userNameText.setEditable(false);
        userNameText.setMaximumSize(new Dimension(
                160,
                20
        ));
        try {
            userNameText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansSemiBold.ttf")))
                    .deriveFont(Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userInfoPane.add(userNameText);

        lastMessageText = new JTextField();
        lastMessageText.setOpaque(false);
        lastMessageText.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        lastMessageText.setEditable(false);

        lastMessageText.setMaximumSize(new Dimension(
                160,
                20
        ));
        lastMessageText.setText("test-text");
        try {
            lastMessageText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 12));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastMessageText.setForeground(new Color(
                120,
                120,
                120
        ));
        userInfoPane.add(lastMessageText);

        lastSeenDateText = new JTextField();
        lastSeenDateText.setOpaque(false);
        lastSeenDateText.setBorder(null);
        lastSeenDateText.setEditable(false);
        lastSeenDateText.setHorizontalAlignment(JTextField.TRAILING);
        lastSeenDateText.setText("10.04.19");
        lastSeenDateText.setPreferredSize(new Dimension(
                50,
                16
        ));
        try {
            lastSeenDateText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
            .deriveFont(Font.PLAIN, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastSeenDateText.setForeground(new Color(
                130,
                130,
                130
        ));
        add(lastSeenDateText);
    }

    public JLabel getUserIcon() {
        return userIcon;
    }

    public JLabel getUserIconMask() {
        return userIconMask;
    }

    public JTextField getUserNameText() {
        return userNameText;
    }

    public JTextField getLastMessageText() {
        return lastMessageText;
    }

    public JTextField getLastSeenDateText() {
        return lastSeenDateText;
    }

    public void setLastMessageText(String text) {
        lastMessageText.setText(text);
        if(lastMessageText.getText().length() > 17) {
            lastMessageText.setText(lastMessageText.getText().substring(0, 18) + "...");
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return null;
    }
}
