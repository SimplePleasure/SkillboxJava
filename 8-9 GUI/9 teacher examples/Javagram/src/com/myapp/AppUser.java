package com.myapp;
import com.myapp.forms.ContactAddPane;
import com.myapp.forms.MainFrame;
import com.myapp.forms.UserEditPane;
import com.myapp.forms.UserMainPane;
import org.javagram.TelegramApiBridge;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;
import org.telegram.api.TLUserContact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class AppUser {
    private User user;
    private TelegramApiBridge bridge;
    private MainFrame mainFrame;

    public AppUser(User user, TelegramApiBridge bridge, MainFrame mainFrame) {
        this.user = user;
        this.bridge = bridge;
        this.mainFrame = mainFrame;
        mainFrame.getMainPane().getUserNameText().setText(
                this.user.getFirstName() + " " +
                        this.user.getLastName());
        mainFrame.getLayer().add(mainFrame.getMainPane(), JLayeredPane.DEFAULT_LAYER);
        mainFrame.getMainPane().getSettings().addActionListener(e -> {
            UserEditPane panel = new UserEditPane();
            panel.getFirstName().setText(AppUser.this.getUser().getFirstName());
            panel.getLastName().setText(AppUser.this.getUser().getLastName());
            JPanel panel1 = new JPanel();
            panel1.setBounds(0, 0, 900, 590);
            panel1.setBackground(new Color(
                    0,
                    0,
                    0,
                    0
            ));
            mainFrame.getLayer().add(panel1, JLayeredPane.MODAL_LAYER);
            mainFrame.getLayer().add(panel, JLayeredPane.POPUP_LAYER);
            panel.getBackButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                }
            });
            panel.getConfirmButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    mainFrame.getMainPane().getUserNameText().setText(
                            panel.getFirstName().getText() + " " +
                                    panel.getLastName().getText()
                    );
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                }
            });
            panel.getLogoutButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    try {
                        bridge.authLogOut();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mainFrame.getLayer().remove(mainFrame.getMainPane());
                    mainFrame.setMainPane(new UserMainPane());
                    mainFrame.getLayer().add(mainFrame.getLoginPane(), JLayeredPane.DEFAULT_LAYER);
                    mainFrame.getLoginPane().getNumber().setText("");
                    mainFrame.getCodePane().getCodeText().setText("");
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                    mainFrame.getLoginPane().getNumber().requestFocus();
                }
            });

            mainFrame.getLayer().revalidate();
            mainFrame.getLayer().repaint();
        });

        mainFrame.getMainPane().getAddButton().addActionListener(e -> {
            ContactAddPane panel = new ContactAddPane(mainFrame);
            JPanel panel1 = new JPanel();
            panel1.setBounds(0, 0, 900, 590);
            panel1.setBackground(new Color(
                    0,
                    0,
                    0,
                    0
            ));
            mainFrame.getLayer().add(panel1, JLayeredPane.MODAL_LAYER);
            mainFrame.getLayer().add(panel, JLayeredPane.POPUP_LAYER);
            panel.getBackButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                }
            });
            panel.getConfirmButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Contact contact = new Contact(
                            new UserContact(new TLUserContact()),
                            mainFrame
                    );
                    contact.setNameText(
                            panel.getFirstName().getText() + " " +
                                    panel.getLastName().getText());
                    contact.getContactPane().getUserNameText().setText(contact.getNameText());
                    contact.getDialogPane().getUserNameText().setText(contact.getNameText());
                    contact.setPhone("+7" + panel.getNumber().getText());
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                }
            });
            mainFrame.getLayer().revalidate();
            mainFrame.getLayer().repaint();
        });
    }

    public User getUser() {
        return user;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}



