import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CheckIn {
    private JPanel rootPanel;
    protected JPanel topPanel;
    private JPanel bottomPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JTextPane text;
    private JTextField numberField;
    private JButton next;
    private JPanel LogoPanel;

    private BufferedImage background;
    private BufferedImage logo;
    private BufferedImage phone;
    private BufferedImage hide;
    private BufferedImage close;


    public CheckIn() {

        try {
            background = ImageIO.read(new File("res/img/background.png"));
            logo = ImageIO.read(new File("res/img/logo.png"));
            phone = ImageIO.read(new File("res/img/icon-phone.png"));
            hide = ImageIO.read(new File("res/img/icon-hide.png"));
            close = ImageIO.read(new File("res/img/icon-close.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        LogoPanel.setBackground( Color.YELLOW);

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        numberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                numberField.setText("+7 ");

                numberField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        super.keyReleased(e);
                        String line;
                        if (numberField.getText().length() == 6) {
                            line = numberField.getText().substring(3);
                            numberField.setText("+7 " + "(" + line + ") ");


                        }
                        if (numberField.getText().length() == 12) {
                            numberField.setText(numberField.getText()+ " ");
                        }
                        if (numberField.getText().length() == 15) {
                            numberField.setText(numberField.getText() + " " );
                        }
                        boolean complete = numberField.getText().length()==18;
                        if (complete) {next.setEnabled(true);}
                        if (!complete) {next.setEnabled(false);}
                    }

                });



            }
        });
    }

    public void setCloseButton(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    public String getNumber() {
        String number = numberField.getText();
        return number;
    }

    public void setButton(ActionListener listener) {
        next.addActionListener(listener);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        bottomPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        LogoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 0, 0, null);
            }
        };
        numberField = new JTextField(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                numberField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                g.setColor(Color.WHITE);
                g.drawRect( 0, 38, 280, 2);
                g.drawImage(phone, 0, 0, null);
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
