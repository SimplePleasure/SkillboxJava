import javax.swing.*;
import java.awt.*;

public class CountryRendener extends JLabel implements ListCellRenderer<Countries> {

    private boolean isSelected;
    Handler handler;

    CountryRendener (Handler h) {
        handler = h;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Countries> list, Countries value, int index, boolean isSelected, boolean cellHasFocus) {

        this.isSelected = isSelected;

        String path = value.country + ".jpg";
        System.out.println(path);
        ImageIcon image = new ImageIcon(getClass().getResource(path));
        setIcon(image);
        setText(value.country);

        if (isSelected) {
            handler.showInfo(index);
        }

        return this;
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if(isSelected) {

            g.setColor(new Color(0,179,230,150));
            g.drawLine( 0, 0, 149, 0);
            g.drawLine(149,0, 149, 31);
            g.drawLine( 149, 31, 0, 31);
            g.setColor(new Color(219, 255, 129, 51));
            g.fillRect(50, 0,119 , 31);

        }

    }
}
