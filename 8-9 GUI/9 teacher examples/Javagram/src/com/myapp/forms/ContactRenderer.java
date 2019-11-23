package com.myapp.forms;

import com.myapp.Contact;

import javax.swing.*;
import java.awt.*;

public class ContactRenderer implements ListCellRenderer<Contact> {
    @Override
    public Component getListCellRendererComponent(
            JList<? extends Contact> list,
            Contact value,
            int index,
            boolean isSelected,
            boolean cellHasFocus

    ) {
        if(value.isOnline) {
            value.getContactPane().getUserIconMask().setIcon(new ImageIcon(
                    "resources/mask-gray-online.png"
            ));
        } else {
            value.getContactPane().getUserIconMask().setIcon(new ImageIcon(
                    "resources/mask-gray.png"
            ));
        }
        value.getContactPane().setBackground(new Color(
                231,
                231,
                231
        ));
        value.getContactPane().setBorder(BorderFactory.createMatteBorder(
                0,
                0,
                1,
                0,
                new Color(
                        210,
                        210,
                        210
                )));

        if(isSelected) {
            if(value.isOnline) {
                value.getContactPane().getUserIconMask().setIcon(new ImageIcon(
                        "resources/mask-white-online.png"
                ));
            } else {
                value.getContactPane().getUserIconMask().setIcon(new ImageIcon(
                        "resources/mask-white.png"
                ));
            }
            value.getContactPane().setBackground(Color.WHITE);
            value.getContactPane().setBorder(BorderFactory.createMatteBorder(
                    0,
                    0,
                    0,
                    5,
                    new Color(
                            0,
                            181,
                            226)
            ));
        }
        return value.getContactPane();
    }
}
