package com.myapp.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainFrame {
    private JFrame frame;
    private JPanel rootPane;
    private JPanel northPane;
    private JLayeredPane layer;
    private JButton hide;
    private JButton close;
    private UserMainPane mainPane;
    private LoginPane loginPane;
    private CodeConfirmPane codePane;
    private UserAddPane userAddPane;

    public MainFrame() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(900, 630);
        frame.setLocationRelativeTo(null);
        Point point = new Point();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = frame.getLocation();
                frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
        rootPane = new JPanel(new BorderLayout());
        rootPane.setBounds(0, 0, 900, 630);
        frame.setContentPane(rootPane);
        northPane = new JPanel();
        northPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 13, 7));
        northPane.setBackground(new Color(
                231,
                231,
                231
        ));
        northPane.setPreferredSize(new Dimension(
                Short.MAX_VALUE,
                33
        ));
        rootPane.add(northPane, BorderLayout.NORTH);

        hide = new JButton();
        hide.setBorder(null);
        hide.setIcon(new ImageIcon(
                "resources/icon-hide.png"
        ));
        hide.setPreferredSize(new Dimension(
                18,
                18));
        hide.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.setState(Frame.ICONIFIED);
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                hide.setBorder(null);
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hide.setBorder(BorderFactory.createLineBorder(
                        Color.BLACK
                ));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hide.setBorder(null);
                super.mouseExited(e);
            }
        });
        northPane.add(hide);

        close = new JButton();
        close.setBorder(null);
        close.setIcon(new ImageIcon(
                "resources/icon-close.png"
        ));
        close.setPreferredSize(new Dimension(
                18,
                18));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                close.setBorder(null);
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                close.setBorder(BorderFactory.createLineBorder(
                        Color.BLACK
                ));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.setBorder(null);
                super.mouseExited(e);
            }
        });
        northPane.add(close);

        layer = new JLayeredPane();
        rootPane.add(layer, BorderLayout.CENTER);

        mainPane = new UserMainPane();
        loginPane = new LoginPane();
        codePane = new CodeConfirmPane();
        userAddPane = new UserAddPane();

        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLayeredPane getLayer() {
        return layer;
    }

    public JPanel getRootPane() {
        return rootPane;
    }

    public UserMainPane getMainPane() {
        return mainPane;
    }

    public LoginPane getLoginPane() {
        return loginPane;
    }

    public CodeConfirmPane getCodePane() {
        return codePane;
    }

    public UserAddPane getUserAddPane() {
        return userAddPane;
    }

    public void setMainPane(UserMainPane mainPane) {
        this.mainPane = new UserMainPane();
    }

    public void setLoginPane(LoginPane loginPane) {
        this.loginPane = new LoginPane();
    }

    public void setCodePane(CodeConfirmPane codePane) {
        this.codePane = new CodeConfirmPane();
    }
}
