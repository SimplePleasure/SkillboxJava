import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class UserRenderer extends JLabel implements ListCellRenderer<Users> {
    private int widht;
    private boolean isSelected;

    @Override
    public Component getListCellRendererComponent(JList<? extends Users> list, Users user, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon selectedImageIcon = new ImageIcon(getClass().getResource("frames/mask-white.png"));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("frames/mask-gray.png"));
        widht = list.getWidth();
        this.isSelected = isSelected;
        if (isSelected) {
            setIcon(imageIcon);
        } else {
            setIcon(selectedImageIcon);
        }
        setText(user.getName());
        return this;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (isSelected) {
            g.setColor(new Color(0,179,230,150));
            g.fillRect( widht-5, 0, 5, 41);
        }
    }


}
