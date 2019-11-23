package com.myapp;

import com.myapp.forms.*;
import org.javagram.response.object.UserContact;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {
    private String nameText;
    private String phone;
    public boolean isOnline;
    private UserContact contact;
    private MainFrame mainFrame;
    private ContactPane contactPane;
    private DialogPane dialogPane;
    private Message[] incMessages;
    private Message[] outMessages;

    public Contact(UserContact contact, MainFrame mainFrame) {
        nameText = contact.getFirstName() + " " + contact.getLastName();
        phone = contact.getPhone();
        isOnline = contact.isOnline();
        this.contact = contact;
        this.mainFrame = mainFrame;
        contactPane = new ContactPane();
        contactPane.getUserNameText().setText(
                this.contact.getFirstName() + " " +
                this.contact.getLastName()
                );
        dialogPane = new DialogPane();
        dialogPane.getUserNameText().setText(
                this.contact.getFirstName() + " " +
                        this.contact.getLastName()
        );
        dialogPane.getMessageSendButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Date date = new Date();
                dialogPane.getDialogPane().add(Message.createMessageOut(
                        dialogPane.getMessageSendText().getText(),
                        new SimpleDateFormat("dd.MM.YY в HH:mm").format(new Date())
                ));
                dialogPane.getMessageSendText().setText("");
                dialogPane.getDialogScroll().getVerticalScrollBar()
                        .addAdjustmentListener(
                                dialogPane.getDownScroller()
                        );
                dialogPane.revalidate();
                dialogPane.repaint();
            }
        });
        dialogPane.getUserEditButton().addActionListener(e -> {
            ContactEditPane panel = new ContactEditPane(this);
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

            panel.getDeleteButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(mainFrame.getMainPane().getContactList().indexOf(Contact.this) == 0) {
                        mainFrame.getMainPane().getJlist().setSelectedIndex(1);
                    } else {
                        mainFrame.getMainPane().getJlist().setSelectedIndex(0);
                    }

                    mainFrame.getMainPane().getContactList().removeElement(Contact.this);
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
                    Contact.this.setNameText(panel.getNameText().getText());
                    dialogPane.getUserNameText().setText(
                            panel.getNameText().getText()
                    );
                    contactPane.getUserNameText().setText(
                            panel.getNameText().getText()
                    );
                    mainFrame.getLayer().remove(panel);
                    mainFrame.getLayer().remove(panel1);
                    mainFrame.getLayer().revalidate();
                    mainFrame.getLayer().repaint();
                }
            });

            mainFrame.getLayer().revalidate();
            mainFrame.getLayer().repaint();

        });
        mainFrame.getMainPane().getContactList().addElement(this);
    }

    public String getNameText() {
        return nameText;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public UserContact getContact() {
        return contact;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public ContactPane getContactPane() {
        return contactPane;
    }

    public DialogPane getDialogPane() {
        return dialogPane;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setDialog() {
        mainFrame.getMainPane().getDialogPane().removeAll();
        mainFrame.getMainPane().getDialogPane().add(this.dialogPane);
        mainFrame.getMainPane().getDialogPane().revalidate();
        mainFrame.getMainPane().getDialogPane().repaint();
    }
}
