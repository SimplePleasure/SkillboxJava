import javax.swing.*;
import java.awt.*;

public class Viev extends JFrame {

    private JPanel rootPanel;
    private JList list;


    Viev(DefaultListModel<CountryPanel> model) {

        list.setModel(model);
        list.setCellRenderer(new JPanelRendener());

        setTitle("test ListCellRendener");
        setSize(240, 400);
        setMinimumSize(new Dimension(240, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setVisible(true);


    }

}
