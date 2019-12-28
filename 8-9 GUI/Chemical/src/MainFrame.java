import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MainFrame extends JFrame {
    BufferedImage background;
    private JPanel rootPanel;
    private JLabel nwField;
    private JLabel owField;
    private JButton btn2;
    private JButton btn1;
    private JLabel p3;
    private JLabel p2;
    private JLabel p1;
    private JComboBox box2;
    private JComboBox box1;
    private JLabel substance;
    private JLabel info;


    HashMap<String, Classifier> classifier;

    {
        try {
            background = ImageIO.read(new File("img/min.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(rootPanel);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        classifier = new HashMap<>();
        classifier.put("H2O", new Classifier("H2O", "Универсальный растворитель"));
        classifier.put("Минеральные в-ва", new Classifier("Минеральные в-ва", "Макро", "Микро", "Ульрта-микро"));
        classifier.put("Углеводы", new Classifier("Углеводы", "Классификация", "Функции"));
        classifier.put("Белки", new Classifier( "Белки", "Классификация", "Структура белковой молекулы", "Функции"));
        classifier.put("Липиды", new Classifier("Липиды", "Классификация", "Функции"));
        classifier.put("Нуклеиновые к-ты", new Classifier("Нуклеиновые к-ты", "классификация", "Функции", "Строение"));
        classifier.put("АТФ и др. соед. кл.", new Classifier("АТФ и др. соед. кл." , "param"));

        classifier.get("Минеральные в-ва").inf1 = "Макро эл-ты";
        classifier.get("Минеральные в-ва").inf2 = "Микро эл-ты";
        classifier.get("Минеральные в-ва").inf3 = "Ультра микро эл-ты";



        box1.addItem("");
        box1.addItem("H2O");
        box1.addItem("Минеральные в-ва");

        box2.addItem("");
        box2.addItem("Углеводы");
        box2.addItem("Белки");
        box2.addItem("Липиды");
        box2.addItem("Нуклеиновые к-ты");
        box2.addItem("АТФ и др. соед. кл.");


    }


    public MainFrame() {

        nwField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                box1.setSelectedIndex(0);
                box1.setVisible(true);
                box2.setVisible(false);
            }

        });

        owField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                box2.setSelectedIndex(0);
                box2.setVisible(true);
                box1.setVisible(false);
            }
        });

        box1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (box1.getSelectedItem().toString().length() == 0) {
                    substance.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                } else {
                    Classifier current = classifier.get(box1.getSelectedItem());
                    substance.setText(current.name);
                    p1.setText(current.param1);
                    if (current.param2 != null) {
                        p2.setText(current.param2);
                        p2.setVisible(true);
                    } else {
                        p2.setVisible(false);
                    }
                    if (current.param3 != null) {
                        p3.setText(current.param3);
                        p3.setVisible(true);
                    } else {
                        p3.setVisible(false);
                    }
                }
            }
        });

        box2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (box2.getSelectedItem().toString().length() == 0) {
                    substance.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                } else {
                    Classifier current = classifier.get(box2.getSelectedItem());
                    substance.setText(current.name);
                    p1.setText(current.param1);
                    if (current.param2 != null) {
                        p2.setText(current.param2);
                        p2.setVisible(true);
                    } else {
                        p2.setVisible(false);
                    }
                    if (current.param3 != null) {
                        p3.setText(current.param3);
                        p3.setVisible(true);
                    } else {
                        p3.setVisible(false);
                    }
                }
            }
        });


        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                info.setText(classifier.get(substance.getText()).inf1);
            }
        });
        p2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                info.setText(classifier.get(substance.getText()).inf2);
            }
        });
        p3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                info.setText(classifier.get(substance.getText()).inf3);
            }
        });
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here

        rootPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(background, 0, 0, 800, 600, null);
            }
        };

        btn1= new JButton(){
            @Override
            protected void paintComponent(Graphics g) {
                btn1.setText("H.B.");
                g.setColor(new Color(203, 79, 0, 200));
                g.fillRect(0, 25, 200, 5);

            }
        };
        btn2 = new JButton(){
            @Override
            protected void paintComponent(Graphics g) {




                g.setColor(new Color(1, 203, 150, 200));
                g.fillRect(0, 25, 200, 5);

                btn2.setText("О.В.");
            }
        };
    }
}
