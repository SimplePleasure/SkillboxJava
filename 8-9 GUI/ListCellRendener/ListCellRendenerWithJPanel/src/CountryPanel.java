import javax.swing.*;
import java.awt.*;

public class CountryPanel {

    private JLabel name;
    private JLabel population;
    private JLabel icon;
    private JPanel rootPanel;
    private JPanel selection;


    CountryPanel(String name, Double population, ImageIcon icon) {
        this.name.setText(name);
        this.population.setText(population + " млн.");
        this.icon.setIcon(icon);
    }

    public void setSelection() {
        selection.setOpaque(true);
        selection.setBackground(new Color(0, 181, 238, 100));
    }
    public void removeSelection() {
        selection.setOpaque(false);
    }

    public String getName() {
        return name.getText();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
