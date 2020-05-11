import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CountryPanel {

    private JLabel name;
    private JLabel index;
    private JLabel icon;
    private JPanel rootPanel;



    CountryPanel(String name, String index, ImageIcon icon) {
        this.name.setText(name);
        this.index.setText(index);
        this.icon.setIcon(icon);
    }

    public String getName() {
        return name.getText();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
