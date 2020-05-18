import javax.swing.*;
import java.awt.*;

public class JPanelRendener extends JPanel implements ListCellRenderer<CountryPanel> {

    @Override
    public Component getListCellRendererComponent(JList<? extends CountryPanel> jList, CountryPanel countryPanel, int i, boolean b, boolean b1) {

        if (b) {
            System.out.println(b);
            countryPanel.setSelection();
        } else {
            countryPanel.removeSelection();
            System.out.println(countryPanel.getName() + " lost");
        }
        return countryPanel.getRootPanel();
    }

}
