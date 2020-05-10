import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


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

    private JButton SendMessage;
    private JTextArea MessageField;
    private JPanel sendMessage;
    private JPanel message3;
    private JPanel message2;
    private JPanel message;
    private JTextPane m3;
    private JTextPane m2;
    private JLabel m;

    private BufferedImage logomicro;
    private BufferedImage settings;
    private BufferedImage hide;
    private BufferedImage close;
    private BufferedImage edit;
    private BufferedImage addButton;
    private BufferedImage messageLeft;
    private BufferedImage messageRight;

    JLayeredPane jlp;


    public MainFrame() {





//        JLayeredPane louredPane = new JLayeredPane();
//        louredPane.setPreferredSize(new Dimension(300, 100));
//        louredPane.setBorder(BorderFactory.createTitledBorder("gsbnsrnr"));
//        louredPane.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
//            }
//        });


        messageField.setLayout(new BoxLayout(messageField, BoxLayout.Y_AXIS));




        try {



            logomicro = ImageIO.read(new File("./res/img/logo-micro.png"));
            settings = ImageIO.read(new File("res/img/icon-settings.png"));
            hide = ImageIO.read(new File("res/img/icon-hide.png"));
            close = ImageIO.read(new File("res/img/icon-close.png"));
            edit = ImageIO.read(new File("res/img/icon-edit.png"));
            addButton = ImageIO.read(new File("res/img/icon-plus.png"));
            messageLeft = ImageIO.read(new File("res/img/message-in-left.png"));
            messageRight = ImageIO.read(new File("res/img/message-out-right.png"));

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


    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        message3 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(1, 167, 217,255));
                g.fillOval( 20, 0, 16, 16);
                g.fillOval( 302, 0, 16, 16);
                g.fillOval( 20, 24, 16, 16);
                g.fillOval( 302, 24, 16, 16);
                g.fillRect(28, 0, 282, 40);
                g.fillRect(20, 8, 298, 24);

                g.drawImage(messageLeft, 13, 15, null);
            }
        };

        message2 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(1, 167, 217,255));
                g.fillOval( 20, 0, 16, 16);
                g.fillOval( 302, 0, 16, 16);
                g.fillOval( 20, 24, 16, 16);
                g.fillOval( 302, 24, 16, 16);
                g.fillRect(28, 0, 282, 40);
                g.fillRect(20, 8, 298, 24);

                g.drawImage(messageLeft, 13, 15, null);
            }
        };

        message = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(74, 68, 168,255));
                g.fillOval(262, 0, 16, 16);
                g.fillOval(530, 0, 16, 16);
                g.fillOval(262, 14, 16, 16);
                g.fillOval(530, 14, 16, 16);
                g.fillRect(270, 0, 270, 30);
                g.fillRect(262, 8, 284, 14);

                g.drawImage(messageRight, 546, 8, null);
            }
        };

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




