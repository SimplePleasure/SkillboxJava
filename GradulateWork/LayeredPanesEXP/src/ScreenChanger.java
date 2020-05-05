import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScreenChanger extends JFrame {

    JPanel mainPanel;
    JLayeredPane layeredPane;
    JButton changeLayer;

    JPanel panel1;
    JLabel jlabelPanel1;

    JPanel panel2;
    JTextField textField;


    ScreenChanger() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
//        mainPanel.setBackground(Color.yellow);
        layeredPane = new JLayeredPane();
        changeLayer = new JButton("change");
        layeredPane.setPreferredSize(new Dimension(300, 140));
//        layeredPane.setBorder(BorderFactory.createTitledBorder("LayeredPane EXP"));

        changeLayer.addActionListener(e -> chahgeLayers());
        createElements();
    }


    public void createElements() {

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//        panel1.setBackground(Color.yellow);
        jlabelPanel1 = new JLabel("Text from first JPanel");
        panel1.add(jlabelPanel1);
        panel1.add(new JLabel("Text from first JPanel"));
        panel1.add(new JLabel("Text from first JPanel"));
        panel1.add(new JLabel("Text from first JPanel"));
        panel1.add(new JLabel("Text from first JPanel"));
        panel1.add(new JLabel("Text from first JPanel"));


        panel1.setBounds(0, 0, 290, 130);


        panel2 = new JPanel();
        panel2.setBounds(0, 25, 275, 70);
//        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
//        panel2.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(2, 1));
        panel2.setOpaque(false);


        textField = new JTextField();
        textField.setForeground(new Color(39, 14, 255, 255));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setOpaque(false);
        textField.setBackground(new Color(29, 50, 160, 37));

        JLabel label = new JLabel("Edit text:\t");
        label.setForeground(new Color(39, 14, 255, 255));
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setLabelFor(textField);

        panel2.add(label, BorderLayout.NORTH);
        panel2.add(textField, BorderLayout.SOUTH);
//        panel2.add(new Tester().getRootPanel(), BorderLayout.NORTH);


        changeLayer.setBounds(205, 100, 60, 20);


        layeredPane.add(panel1, new Integer(3));
        layeredPane.add(panel2, new Integer(2));
        layeredPane.add(changeLayer, new Integer(4));
        mainPanel.add(layeredPane);

        showPanel();

    }


    public void showPanel() {

        setTitle("Testing");
        setSize(new Dimension(275, 150));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);

//        revalidate();
//        repaint();

    }

    public void chahgeLayers() {


        int paneLayer1 = layeredPane.getLayer(panel1);

        if (paneLayer1 == 3) {
            panel1.setBackground(new Color(34, 63, 95, 126));
            for (Component c : panel1.getComponents()) {
                c.setForeground(new Color(20, 46, 69, 128));
            }
            layeredPane.setLayer(panel1, new Integer(1), 0);
//            revalidate();
//            repaint();
        } else {
            if (textField.getText().length() > 0) {
                jlabelPanel1.setText(textField.getText());
                textField.setText("");
            }
            panel1.setBackground(null);
//            panel1.getComponent(0).setForeground(null);
            for (Component c : panel1.getComponents()) {
                c.setForeground(null);
            }

            layeredPane.setLayer(panel1, new Integer(3), 0);
        }
    }


}
