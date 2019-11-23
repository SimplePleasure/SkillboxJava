import javax.swing.*;
import java.awt.*;



public class MessageRendener extends JLabel implements ListCellRenderer<String>  {
    private int width;
    private int height;

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {

        //list.setFont( new Font("monospaced", Font.PLAIN, 10) );
        width = list.getWidth();
        height = list.getHeight();



        setHorizontalTextPosition(JLabel.RIGHT);
        setText(value);
        if (isSelected) {
            System.out.println(index);
        }

        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(100, 100, 200, 100));
        g.fillRect( 2, 2, width-4, height-4);


    }
}
