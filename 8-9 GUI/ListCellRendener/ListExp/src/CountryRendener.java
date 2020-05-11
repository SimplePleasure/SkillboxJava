//import javax.swing.*;
//import java.awt.*;
//
//public class CountryRendener extends JLabel implements ListCellRenderer<Country> {
//
//
//    @Override
//    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
//                                                  boolean isSelected, boolean cellHasFocus) {
//
//        String code = country.getCountryCode();
//        System.out.println("/img/" + code + ".png");
//        ImageIcon imageIcon = new ImageIcon(getClass().getResource(code + ".png"));
//        setIcon(imageIcon);
//        setText(country.getCountryName());
//
//        setForeground(new Color(255, 0, 0));
//        setSize(10, 5);
//
//        return this;
//    }
//}



 /*
 TODO
  */


import javax.swing.*;
        import java.awt.*;

public class CountryRendener extends JPanel implements ListCellRenderer<Country> {
    boolean isSelected;
    boolean cellFocus;

    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        this.isSelected = isSelected;
        this.cellFocus = cellHasFocus;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



//        JLabel label = new JLabel("________________some test");
//        add(label);
        JLabel countryL = new JLabel(country.getCountryName());
        countryL.setForeground(new Color(0, 76, 255));
        add(countryL);
        setSize(10,5);




        return this;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected) {
            g.setColor(new Color(179, 113, 255, 79));
            g.fillRect(0,0, 50, 15);
        }
        if (cellFocus) {
            g.setColor(new Color(113, 255, 251, 79));
            g.fillRect(0,0, 50, 15);
        }
    }
}
