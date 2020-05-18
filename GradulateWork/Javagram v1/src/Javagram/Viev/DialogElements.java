package Javagram.Viev;

import Javagram.AppSettings.Config;
import Javagram.interfaces.MainInterface;
import org.javagram.response.object.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Comparator;

public class DialogElements implements MainInterface.IDialogs {

    static Color rightLine = new Color(0, 181, 238, 100);
    static Color fill = new Color(159, 198, 120, 50);

    int userId;
    boolean isOnline;
    String phone;
    String firstName;
    String lastName;
    Message message;

    private JPanel rootPanel;
    private JLabel photoLabel;
    private JLabel name;
    private JLabel lastMessage;
    private JPanel info;
    private JPanel selection;


    public DialogElements(int userId, boolean isOnline, String phone, String firstName, String lastName, Message lastMessage) {
        this.userId = userId;
        this.isOnline = isOnline;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        message = lastMessage;
        this.lastMessage.setText(lastMessage.getMessage());
        name.setText(firstName + " " + lastName);
    }


    public int getUserId() {
        return userId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public Message getLastMessage() {
        return message;
    }

    public void setPhoto(byte[] photo) {

        int size = 45;
        Image i = new ImageIcon(photo).getImage();
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(i, 0, 0, size, size, null);
        if (isOnline) {
            g2.drawImage(Config.maskGrayOnline, 0, 0, size, size, null);
        } else {
            g2.drawImage(Config.maskGray, 0, 0, size, size, null);
        }
        g2.dispose();
        photoLabel.setIcon(new ImageIcon(img));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void setUISelection() {
        info.setOpaque(true);
        info.setBackground(fill);
        selection.setOpaque(true);
        selection.setBackground(rightLine);
    }

    @Override
    public void removeUISelection() {
        info.setOpaque(false);
        selection.setOpaque(false);
    }


    // TODO: 16.05.2020 add compare objects by time
}
