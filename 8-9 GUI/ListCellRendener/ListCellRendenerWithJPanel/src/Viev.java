import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Viev extends JFrame {

    private JPanel rootPanel;
    private JList list;


    Viev(DefaultListModel<JPanel> model) {

        list.setModel(model);
        list.setCellRenderer(new JPanelRendener());

        setTitle("test ListCellRendener");
        setSize(240, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setVisible(true);


    }

}
