import javax.swing.*;
import java.awt.*;

public class CountryRendener extends JLabel implements ListCellRenderer<Country> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
                                                                        boolean isSelected, boolean cellHasFocus) {

        String code = country.getCountryCode();
        System.out.println( "/img/" + code + ".png");
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(  code + ".png"));
        setIcon(imageIcon);
        setText(country.getCountryName());
        return this;
    }



}
