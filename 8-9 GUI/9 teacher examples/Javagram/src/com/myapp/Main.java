package com.myapp;

import com.myapp.forms.MainFrame;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static AppUser user;
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        MainFrame mainPane = new MainFrame();
        TelegramApiBridge bridge = new TelegramApiBridge(
                "149.154.167.40:443",
                461984,
                "746eb50f5f37eb3d867dd38ea0f15a61"
    );

        mainPane.getLayer().add(mainPane.getLoginPane(), JLayeredPane.DEFAULT_LAYER);
        mainPane.getFrame().setVisible(true);
        mainPane.getFrame().revalidate();
        mainPane.getFrame().repaint();
        mainPane.getLoginPane().getNumber().requestFocus();

        mainPane.getLoginPane().getConfirmButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AuthCheckedPhone checkedPhone = null;
                try {
                    checkedPhone = bridge.authCheckPhone(
                            "7" + mainPane.getLoginPane().getNumber().getText()
                            );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(mainPane.getLoginPane().getNumber().getText().length() == 10
                        && checkedPhone.isRegistered()) {
                    try {
                        bridge.authSendCode(
                                "7" + mainPane.getLoginPane().getNumber().getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mainPane.getLayer().remove(mainPane.getLoginPane());
                    mainPane.getLayer().add(mainPane.getCodePane(), JLayeredPane.DEFAULT_LAYER);
                    mainPane.getCodePane().getNumberText().setText(
                            "+7" + " " +
                                    mainPane.getLoginPane().getNumber().getText().substring(0, 3) + " " +
                                    mainPane.getLoginPane().getNumber().getText().substring(3, 6) + "-" +
                                    mainPane.getLoginPane().getNumber().getText().substring(6, 8) + "-" +
                                    mainPane.getLoginPane().getNumber().getText().substring(8)
                    );

                    mainPane.getCodePane().getCodeText().requestFocus();
                    mainPane.getLayer().revalidate();
                    mainPane.getLayer().repaint();
                } else {
                    mainPane.getLayer().remove(mainPane.getLoginPane());
                    mainPane.getLayer().add(mainPane.getUserAddPane(), JLayeredPane.DEFAULT_LAYER);
                    mainPane.getUserAddPane().getFirstName().requestFocus();
                    mainPane.getLayer().revalidate();
                    mainPane.getLayer().repaint();
                }
            }
        });

        mainPane.getCodePane().getConfirmButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    AuthAuthorization authAuthorization = bridge.authSignIn(
                            mainPane.getCodePane().getCodeText().getText()
                    );
                    user = new AppUser(authAuthorization.getUser(), bridge, mainPane);
                    ArrayList<UserContact> userContacts = bridge.contactsGetContacts();
                    for(UserContact c: userContacts) {
                       contacts.add(new Contact(c, mainPane));
                    }
                    mainPane.getLayer().remove(mainPane.getCodePane());
                    mainPane.getMainPane().getJlist().setSelectedIndex(0);
                    mainPane.getLayer().revalidate();
                    mainPane.getLayer().repaint();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        mainPane.getFrame().revalidate();
        mainPane.getFrame().repaint();
    }
}
