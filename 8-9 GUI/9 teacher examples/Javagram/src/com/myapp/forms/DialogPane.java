package com.myapp.forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogPane extends JPanel {
    private JPanel userPane;
    private JPanel userInfoPane;
    private JPanel userIconPane;
    private JLabel userIcon;
    private JLabel userIconMask;
    private LayoutManager layout;
    private JPanel userEditPane;
    private JTextField userNameText;
    private JButton userEditButton;
    private AdjustmentListener downScroller;
    private JScrollPane dialogScroll;
    private JPanel dialogPane;
    private JPanel messageSendPane;
    private JTextArea messageSendText;
    private JLabel messageSendButton;

    public DialogPane() {
        setLayout(new BorderLayout());

        userPane = new JPanel();
        userPane.setBackground(Color.WHITE);
        userPane.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                1,
                0,
                new Color(
                        236,
                        236,
                        236
                )
        ));
        userPane.setLayout(new BoxLayout(userPane, BoxLayout.X_AXIS));
        userPane.setPreferredSize(new Dimension(
                Short.MAX_VALUE,
                47
        ));
        add(userPane, BorderLayout.NORTH);

        userInfoPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 9));
        userInfoPane.setOpaque(false);
        userPane.add(userInfoPane);

        userIconPane = new JPanel();
        layout = new OverlayLayout(userIconPane);
        userIconPane.setLayout(layout);
        userIconPane.setPreferredSize(new Dimension(
                29,
                29
        ));
        userInfoPane.add(userIconPane);

        userIconMask = new JLabel();
        userIconMask.setOpaque(false);
        userIconMask.setIcon(new ImageIcon("resources/mask-white-mini.png"));
        userIconPane.add(userIconMask);

        userIcon = new JLabel();
        userIcon.setOpaque(false);
        userIconPane.add(userIcon);

        userNameText = new JTextField();
        userNameText.setOpaque(false);
        userNameText.setBorder(null);
        userNameText.setPreferredSize(new Dimension(
                250,
                20
        ));
        userNameText.setEditable(false);
        try {
            userNameText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userNameText.setForeground(new Color(
                120,
                120,
                120
        ));
        userInfoPane.add(userNameText);

        userEditPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 14));
        userEditPane.setOpaque(false);
        userPane.add(userEditPane);

        userEditButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                       "resources/icon-edit.png"
                    ));
                    g.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        userEditButton.setContentAreaFilled(false);
        userEditButton.setBorderPainted(false);
        userEditButton.setPreferredSize(new Dimension(
                18,
                18
        ));
        userEditPane.add(userEditButton);

        dialogPane = new JPanel();
        dialogPane.setBackground(Color.WHITE);
        dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));
        dialogPane.add(Box.createVerticalGlue());

        downScroller = new AdjustmentListener()  {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
                Adjustable adjustable = adjustmentEvent.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                dialogScroll.getVerticalScrollBar().removeAdjustmentListener(this);
            }
        };

        dialogScroll = new JScrollPane(
                dialogPane,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER

        );
        dialogScroll.getVerticalScrollBar().setUnitIncrement(15);
        dialogScroll.setBorder(null);
        dialogScroll.setComponentZOrder(dialogScroll.getVerticalScrollBar(), 0);
        dialogScroll.setComponentZOrder(dialogScroll.getViewport(), 1);
        dialogScroll.getVerticalScrollBar().setOpaque(false);

        dialogScroll.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane)parent;

                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;

                Insets insets = parent.getInsets();
                availR.x = insets.left;
                availR.y = insets.top;
                availR.width  -= insets.left + insets.right;
                availR.height -= insets.top  + insets.bottom;

                Rectangle vsbR = new Rectangle();
                vsbR.width  = 12;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;

                if(viewport != null) {
                    viewport.setBounds(availR);
                }
                if(vsb != null) {
                    vsb.setVisible(true);
                    vsb.setBounds(vsbR);
                }
            }
        });

        dialogScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            private final Dimension d = new Dimension();
            @Override protected JButton createDecreaseButton(int orientation) {
                return new JButton() {
                    @Override public Dimension getPreferredSize() {
                        return d;
                    }
                };
            }
            @Override protected JButton createIncreaseButton(int orientation) {
                return new JButton() {
                    @Override public Dimension getPreferredSize() {
                        return d;
                    }
                };
            }
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D)g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                JScrollBar sb = (JScrollBar)c;
                Color color = new Color(160,160,160, 150);
                g2.setPaint(color);
                g2.fillRoundRect(r.x,r.y,r.width,r.height,12,12);
                g2.dispose();
            }
            @Override
            protected void setThumbBounds(int x, int y, int width, int height) {
                super.setThumbBounds(x, y, width, height);
                scrollbar.repaint();
            }
        });

        add(dialogScroll, BorderLayout.CENTER);

        messageSendPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        messageSendPane.setBackground(Color.WHITE);
        add(messageSendPane, BorderLayout.SOUTH);

        messageSendText = new JTextArea(1, 28) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(
                        223,
                        223,
                        223
                ));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                super.paintComponent(g);
            }
        };
        messageSendText.setOpaque(false);
        messageSendText.setMargin(new Insets(13, 20, 13 ,20));
        messageSendText.setLineWrap(true);
        messageSendText.setWrapStyleWord(true);
        try {
            messageSendText.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageSendPane.add(messageSendText);

        messageSendButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    BufferedImage icon = ImageIO.read(new File(
                       "resources/button-send.png"
                    ));
                    g.drawImage(icon, 1, 1, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.paintComponent(g);
            }
        };
        messageSendButton.setPreferredSize(new Dimension(
                63,
                46
        ));
        messageSendPane.add(messageSendButton);
    }

    public JPanel getUserPane() {
        return userPane;
    }

    public JPanel getUserInfoPane() {
        return userInfoPane;
    }

    public JPanel getUserIconPane() {
        return userIconPane;
    }

    public JLabel getUserIcon() {
        return userIcon;
    }

    public JLabel getUserIconMask() {
        return userIconMask;
    }

    @Override
    public LayoutManager getLayout() {
        return layout;
    }

    public JPanel getUserEditPane() {
        return userEditPane;
    }

    public JTextField getUserNameText() {
        return userNameText;
    }

    public JButton getUserEditButton() {
        return userEditButton;
    }

    public AdjustmentListener getDownScroller() {
        return downScroller;
    }

    public JScrollPane getDialogScroll() {
        return dialogScroll;
    }

    public JPanel getDialogPane() {
        return dialogPane;
    }

    public JPanel getMessageSendPane() {
        return messageSendPane;
    }

    public JTextArea getMessageSendText() {
        return messageSendText;
    }

    public JLabel getMessageSendButton() {
        return messageSendButton;
    }

    public void createTestDialog() {
        Message messageIn = Message.createMessageIn(
                "shkfhfkhskfhshkhkhshkfkshkfhkshkhfkshfhkshfsdgsdgsdggs",
                new SimpleDateFormat("dd.MM.YY в HH:mm").format(new Date()));
        dialogPane.add(messageIn);

        Message messageOut = Message.createMessageOut(
                "dsjdlsldjsjldjjsdjsldjlsdjsjldjsjljldjsjdsjlggdgdgdgdgdgd",
                new SimpleDateFormat("dd.MM.YY в HH:mm").format(new Date()));
        dialogPane.add(messageOut);
    }
}
