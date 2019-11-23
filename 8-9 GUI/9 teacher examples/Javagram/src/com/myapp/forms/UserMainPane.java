package com.myapp.forms;

import com.myapp.Contact;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UserMainPane extends JPanel {
    private JPanel userInfoPane;
    private LayoutManager overlay;
    private JPanel userIconPane;
    private JLabel userIcon;
    private JLabel userIconMask;
    private JTextField userNameText;
    private JButton settings;
    private JPanel contactPane;
    private JPanel searchPane;
    private DefaultListModel<Contact> contactList;
    private JList<Contact> jlist;
    private JScrollPane contactScroll;
    private JPanel searchIcon;
    private JTextField searchField;
    private JPanel addPane;
    private JButton addButton;
    private JPanel dialogPane;

    public JPanel getDialogPane() {
        return dialogPane;
    }

    public UserMainPane() {
        setLayout(new BorderLayout());
        setBounds(0, 0, 900, 597);

        userInfoPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 9)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/logo-micro.png"
                    ));
                    g.drawImage(icon, 25, 12, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        userInfoPane.setBackground(new Color(
                0,
                181,
                226
        ));
        userInfoPane.setPreferredSize(new Dimension(
                0,
                48
        ));
        add(userInfoPane, BorderLayout.NORTH);

        userIconPane = new JPanel();
        overlay = new OverlayLayout(userIconPane);
        userIconPane.setLayout(overlay);
        userIconPane.setPreferredSize(new Dimension(29, 29));
        userInfoPane.add(userIconPane);

        userIconMask = new JLabel();
        userIconMask.setOpaque(false);
        userIconMask.setIcon(new ImageIcon("resources/mask-blue-mini.png"));
        userIconMask.setPreferredSize(new Dimension(
                29,
                29
        ));
        userIconPane.add(userIconMask);

        userIcon = new JLabel();
        userIcon.setPreferredSize(new Dimension(
                29,
                29
        ));
        userIconPane.add(userIcon);

        userNameText = new JTextField();
        userNameText.setOpaque(false);
        userNameText.setBorder(null);
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
        userNameText.setForeground(Color.WHITE);
        userInfoPane.add(userNameText);

        settings = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage icon =  ImageIO.read(new File(
                       "resources/icon-settings.png"
                    ));
                    g.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        settings.setContentAreaFilled(false);
        settings.setBorderPainted(false);
        settings.setPreferredSize(new Dimension(
                21,
                21
        ));
        userInfoPane.add(settings);

        contactPane = new JPanel(new BorderLayout());
        contactPane.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                0,
                1,
                 new Color(
                         210,
                         210,
                         210
                 )
        ));
        contactPane.setPreferredSize(new Dimension(
                260,
                Short.MAX_VALUE
        ));
        add(contactPane, BorderLayout.WEST);

        searchPane = new JPanel();
        searchPane.setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                1,
                0,
                new Color(
                        210,
                        210,
                        210
                )
        ));
        searchPane.setBackground(Color.WHITE);
        searchPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 14));
        searchPane.setPreferredSize(new Dimension(
                Short.MAX_VALUE,
                47
        ));
        contactPane.add(searchPane, BorderLayout.NORTH);

        searchIcon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                            "resources/icon-search.png"
                    ));
                    g.drawImage(icon, 10, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        searchIcon.setOpaque(false);
        searchIcon.setPreferredSize(new Dimension(
                30,
                20

        ));
        searchPane.add(searchIcon);

        searchField = new JTextField();
        searchField.setOpaque(false);
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(
                150,
                20
        ));
        try {
            searchField.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    new FileInputStream(new File(
                            "resources/OpenSansRegular.ttf")))
                    .deriveFont(Font.PLAIN, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchField.setForeground(new Color(
                160,
                160,
                160
        ));
        searchField.setText("Поиск");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchField.setText("Поиск");
            }
        });
        searchPane.add(searchField);

        contactList = new DefaultListModel<>();
        jlist = new JList<>(contactList);
        jlist.setBackground(new Color(
                231,
                231,
                231
        ));
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
               JList<Contact> list = (JList) e.getSource();
               Contact user = (Contact) list.getSelectedValue();
               user.setDialog();
               user.getDialogPane().getDialogScroll().getViewport()
                       .setViewPosition(new Point(
                               0,
                               user.getDialogPane().getDialogScroll().getVerticalScrollBar().getMaximum()
                       ));
               revalidate();
               repaint();
            }
        });
        jlist.setCellRenderer(new ContactRenderer());

        contactScroll = new JScrollPane(
                jlist,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        contactScroll.setBorder(null);
        contactScroll.setComponentZOrder(contactScroll.getVerticalScrollBar(), 0);
        contactScroll.setComponentZOrder(contactScroll.getViewport(), 1);
        contactScroll.getVerticalScrollBar().setOpaque(false);

        contactScroll.setLayout(new ScrollPaneLayout() {
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

        contactScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        contactPane.add(contactScroll, BorderLayout.CENTER);

        addPane = new JPanel();
        addPane.setLayout(new FlowLayout(FlowLayout.LEFT, 23, 10));
        addPane.setPreferredSize(new Dimension(
                Short.MAX_VALUE,
                60
        ));
        addPane.setBackground(new Color(
                231,
                231,
                231
        ));
        contactPane.add(addPane, BorderLayout.SOUTH);

        addButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage icon = ImageIO.read(new File(
                       "resources/icon-plus.png"
                    ));
                    g.drawImage(icon, 0, 0, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        addButton.setPreferredSize(new Dimension(
                26,
                26
        ));
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        addPane.add(addButton);

        dialogPane = new JPanel(new BorderLayout());
        add(dialogPane, BorderLayout.CENTER);
    }

    public JPanel getUserInfoPane() {
        return userInfoPane;
    }

    public LayoutManager getOverlay() {
        return overlay;
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

    public JTextField getUserNameText() {
        return userNameText;
    }

    public JButton getSettings() {
        return settings;
    }

    public JPanel getContactPane() {
        return contactPane;
    }

    public JPanel getSearchPane() {
        return searchPane;
    }

    public DefaultListModel<Contact> getContactList() {
        return contactList;
    }

    public JList<Contact> getJlist() {
        return jlist;
    }

    public JScrollPane getContactScroll() {
        return contactScroll;
    }

    public JPanel getSearchIcon() {
        return searchIcon;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JPanel getAddPane() {
        return addPane;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
