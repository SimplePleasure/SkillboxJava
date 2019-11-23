import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetName {
    private JPanel RootPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JTextPane typeName;
    private JPanel dataPanel;
    private JPanel photoPanel;
    private JPanel namePanel;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton continueButton;
    private JPanel logoPanel;

    private BufferedImage background;
    private BufferedImage logo;
    private BufferedImage usericon;
    private BufferedImage hide;
    private BufferedImage close;

    public GetName() {

        try {
            background = ImageIO.read(new File("res/img/background.png"));
            logo = ImageIO.read(new File("res/img/logo-mini.png"));
            usericon = ImageIO.read(new File("res/img/icon-usert.png"));
            hide = ImageIO.read(new File("res/img/icon-hide.png"));
            close = ImageIO.read(new File("res/img/icon-close.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        logoPanel.setBackground(Color.YELLOW);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));



        nameField.setText("Имя");
        surnameField.setText("Фамилия");


        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                nameField.setText("");
            }
        });
        surnameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                surnameField.setText("");
            }
        });



        surnameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!nameField.getText().equals("Имя") && !surnameField.getText().equals("Фамилия")) {
                    boolean complete = !nameField.getText().isEmpty() && !surnameField.getText().isEmpty();
                    if (complete) {
                        continueButton.setEnabled(true);
                    }
                    if (!complete) {
                        continueButton.setEnabled(false);
                    }
                }
            }
        });
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!nameField.getText().equals("Имя") && !surnameField.getText().equals("Фамилия")) {
                    boolean complete = !nameField.getText().isEmpty() && !surnameField.getText().isEmpty();
                    if (complete) {
                        continueButton.setEnabled(true);
                    }
                    if (!complete) {
                        continueButton.setEnabled(false);
                    }
                }
            }
        });


    }

    public String getName() {
        String name = nameField.getText() + " " + surnameField.getText();
        return name;
    }

    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public void setButton(ActionListener listener){
        continueButton.addActionListener(listener);
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }

    private void createUIComponents() {
         bottomPanel = new JPanel(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(background, 0, 0, null);
             }
         };
         logoPanel = new JPanel(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(logo, 0, 0, null);
             }
         };
         nameField = new JTextField(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                 g.setColor(Color.WHITE);
                 g.drawRect( 0, 28, 200, 2);
             }
         };
         surnameField = new JTextField(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 surnameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                 g.setColor(Color.WHITE);
                 g.drawRect( 0, 28, 200, 2);
             }
         };
         photoPanel = new JPanel(){
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.drawImage(usericon, 0, 0, null);
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
    }
}
