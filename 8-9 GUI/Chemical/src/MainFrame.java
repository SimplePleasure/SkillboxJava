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
    private JLabel p3;
    private JLabel p2;
    private JLabel p1;
    private JComboBox<String> box2;
    private JComboBox<String> box1;
    private JLabel substanceName;
    private JTextArea info;


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

//        nw
        Classifier h2o = classifier.get("H2O");
        h2o.inf1 = "  unique раст";

        Classifier minW = classifier.get("Минеральные в-ва");
        minW.inf1 = "Макро \nэл-ты";
        minW.inf2 = "Микро эл-ты";
        minW.inf3 = "Ультра микро эл-ты";
//        ow
        Classifier uglevod = classifier.get("Углеводы");
        uglevod.inf1 = "    Классификация углеводов: \n\n\n" +
                "Моносахариды: \n       рибоза, дезоксирибоза, глюкоза, фруктоза, галактоза.\n\n" +
                "Олигосахариды (дисахариды):\n      сахароза, мальтоза, лактоза. \n\n" +
                "Полисахариды:\n        крахмал, гликоген, целлюлоза, хитин и другие.";
        uglevod.inf2 = "Функции углеводов: \n Энергетическая, запасающая, структурно-строительная и защитная";

        Classifier belki = classifier.get("Белки");
        belki.inf1 = "  Классификация белков: \n Простые - протеины, протеиды\n" +
                "Сложные - Гликопротеиды, липопротеиды, нуклеопротеиды";
        belki.inf2 = "Уровни организации с белковые молекулы заменить пункт найти картинки.";
        belki.inf3 = "  Функции белков: \nСтруктурная, ферментативная, транспортная, защитная, регуляторная, энергетическая";

        Classifier lipid = classifier.get("Липиды");
        lipid.inf1 = "";
        lipid.inf2 = "";

        Classifier nukl = classifier.get("Нуклеиновые к-ты");
        nukl.inf1 = "";
        nukl.inf2 = "";
        nukl.inf3 = "";

        Classifier atf = classifier.get("АТФ и др. соед. кл.");
        atf.inf1 = "";





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
                    substanceName.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                } else {
                    Classifier current = classifier.get(box1.getSelectedItem());
                    substanceName.setText(current.name);
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
                    substanceName.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                } else {
                    Classifier current = classifier.get(box2.getSelectedItem());
                    substanceName.setText(current.name);
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
                info.setText(classifier.get(substanceName.getText()).inf1);
            }
        });
        p2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                info.setText(classifier.get(substanceName.getText()).inf2);
            }
        });
        p3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                info.setText(classifier.get(substanceName.getText()).inf3);
            }
        });
    }


}
