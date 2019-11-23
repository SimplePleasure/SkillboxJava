import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class MainFrame extends JPanel{
    private JPanel rootPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel contactPanel;
    private JPanel dialogWindow;
    private JList list;
    private JPanel UserNamePanel;
    private JLabel userName;
    private JButton Add;
    private JPanel nameContactPane;
    private JButton editContact;
    private JLabel contactName;
    private JPanel micrologoPanel;
    private JButton userSettingsButton;
    private JPanel userPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JSplitPane splitPane;
    private JPanel messageField;
    private JList MessageList;
    private JPanel sendMessage;
    private JButton SendMessage;
    private JTextArea MessageField;

    private BufferedImage logomicro;
    private BufferedImage settings;
    private BufferedImage hide;
    private BufferedImage close;
    private BufferedImage edit;
    private BufferedImage addButton;


    public MainFrame() {


        try {


            logomicro = ImageIO.read(new File("./res/img/logo-micro.png"));
            settings = ImageIO.read(new File("res/img/icon-settings.png"));
            hide = ImageIO.read(new File("res/img/icon-hide.png"));
            close = ImageIO.read(new File("res/img/icon-close.png"));
            edit = ImageIO.read(new File("res/img/icon-edit.png"));
            addButton = ImageIO.read(new File("res/img/icon-plus.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }





        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println(list.getSelectedIndex());
                if (list.getSelectedIndex() >= 0) {
                    contactName.setText(list.getSelectedValue().toString());
                    rightPanel.setVisible(true);

                } else {
                    rightPanel.setVisible(false);
                }
            }
        });
    }



    public void setMessageList (ArrayList<String> messages) {
        DefaultListModel<String> model = new DefaultListModel<>();
        MessageList.setModel(model);
        MessageList.setCellRenderer(new MessageRendener());
        for (String message : messages) {
            model.addElement(message);
            System.out.println("элемент" + message + "добавлен");
        }
    }






    public void setContactList(ArrayList<Users> arrayList){
        DefaultListModel<Users> model = new DefaultListModel<>();
        list.setModel(model);
        list.setCellRenderer(new UserRenderer());
        for (Users user: arrayList){
            model.addElement(user);
        }
    }















    public String getSelectedContact() {
        return list.getSelectedValue().toString();
    }


    public void setUserName(String name){
        userName.setText(name);
    }

    public void setEditContactButton(ActionListener listener){
        editContact.addActionListener(listener);
    }

    public void settingsButton(ActionListener listener){
        userSettingsButton.addActionListener(listener);
    }

    public void addContact(ActionListener listener) {
        Add.addActionListener(listener);
    }

    public void SendMessageButton (ActionListener listener) {
        SendMessage.addActionListener(listener);
    }

    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
         micrologoPanel = new JPanel(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(logomicro, 10, 5, null);
             }
         };
         userSettingsButton = new JButton(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(settings, 0, 6, null);
             }
         };
         rollupButton = new JButton(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(hide, 1, 1, null);
             }
         };
         closeButton = new JButton(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(close, 1, 1, null);
             }
         };
         editContact = new JButton(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(edit, 6, 6, null);
             }
         };
         Add = new JButton(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(addButton, 0, 4, null);
             }
         };

    }
}
