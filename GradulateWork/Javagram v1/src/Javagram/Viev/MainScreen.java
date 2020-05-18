package Javagram.Viev;

import Javagram.AppSettings.Config;
import Javagram.Storage.ABSUserData;
import Javagram.TgAPI.TelegramAPI;
import Javagram.interfaces.MainInterface;
import org.javagram.response.object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MainScreen implements MainInterface.IMainScreen {

    private JPanel rootPanel;
    private JList list;
    private JSplitPane splitPane;
    private JPanel logoPanel;
    private JLabel myIcon;
    private JLabel settings;
    private JPanel searchPanel;
    private JTextField searchTextField;
    private JLabel searchLabel;
    private JLabel editUser;
    private JLabel userInfo;
    private JTextArea textArea1;


    public MainScreen() {

        WindowHandler.setContainer(this);
        init();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        splitPane.getLeftComponent().setMinimumSize(new Dimension(50, 45));
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        searchTextField.setBorder(BorderFactory.createEmptyBorder());
        myIcon.setText(ABSUserData.getStorage().getFirstName() + " " + ABSUserData.getStorage().getLastName());

        searchLabel.setIcon(new ImageIcon(Config.search));
        myIcon.setIcon(new ImageIcon(Config.maskBlueMini));
        settings.setIcon(new ImageIcon(Config.settings));
        editUser.setIcon(new ImageIcon(Config.edit));


        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    MainInterface.IDialogs dialog = (MainInterface.IDialogs) list.getSelectedValue();
                    UserContact user = ABSUserData.getStorage().getUserById(dialog.getUserId());
                    userInfo.setText(user.getFirstName() + " " + user.getLastName());
                    if (user.getPhoto(true) != null) {
                        userInfo.setIcon(prepareIcon(new ImageIcon(user.getPhoto(true)).getImage(),
                                Config.maskWhiteMini));
                    } else {
                        userInfo.setIcon(prepareIcon(Config.standartPhoto, Config.maskWhiteMini));
                    }




                    ArrayList<Integer> id = new ArrayList<>();
                    System.out.println(dialog.getLastMessage().getId());
                    IntStream.range(dialog.getLastMessage().getId()-200, dialog.getLastMessage().getId()).forEach(id::add);
                    System.err.println("Ids size: "+id.size());
                    ArrayList<Message> messages = TelegramAPI.getBridge().messagesGetMessages(id);
                    System.err.println("Mess size" + messages.size());

//                    for (Message m : messages) {
//                        if (m.getFromId() == dialog.getUserId() || m.getToId() == dialog.getUserId()) {
//                            System.err.println(m.getMessage() + "\t" + m.getId());
//                        } else {
//                            System.out.println("expected: " + dialog.getUserId() + "\n receieved: " + m.getToId() +"\t"+ m.getFromId());
//                            System.out.println(m.getMessage());
//                        }
//                    }
                    for (Message m : messages) {
                        if (m.getFromId() == dialog.getUserId() || m.getToId() == dialog.getUserId()) {
                            textArea1.append(m.getMessage() + "\tdate: " + m.getDate() + "\n");
                        }
                    }


                } catch (IOException ioeException) {
                    ioeException.printStackTrace();
                }
            }
        });

    }















    public void init() {

        List<Message> messageList = ABSUserData.getStorage().getLastMessageList();
        DefaultListModel<MainInterface.IDialogs> model = new DefaultListModel<>();

        try {
            for (Message m : messageList) {

                UserContact from = ABSUserData.getStorage().getUserById(m.getFromId());
                UserContact to = ABSUserData.getStorage().getUserById(m.getToId());
                if (from != null) {
                    MainInterface.IDialogs dialogElement = new DialogElements(from.getId(), from.isOnline(), from.getPhone(),
                            from.getFirstName(), from.getLastName(), m);
                    byte[] photo = from.getPhoto(true);
                    if (photo != null && photo.length > 0) {
                        dialogElement.setPhoto(photo);
                    }
                    model.addElement(dialogElement);
                }
                if (to != null) {
                    MainInterface.IDialogs dialogElement = new DialogElements(to.getId(), to.isOnline(), to.getPhone(),
                            to.getFirstName(), to.getLastName(), m);
                    byte[] photo = to.getPhoto(true);
                    if (photo != null && photo.length > 0) {
                        dialogElement.setPhoto(photo);
                    }
                    model.addElement(dialogElement);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.setModel(model);
        list.setCellRenderer(new DialogCellRendener());
    }

    public ImageIcon prepareIcon(Image icon, BufferedImage overlayImg) {
        int size = 29;
        Image img = new ImageIcon(icon).getImage();
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, size, size, null);
        g2.drawImage(overlayImg, 0, 0, size, size, null);
        return new ImageIcon(image);
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Config.logoMicro, 5, 6, null);
            }
        };

    }
}
