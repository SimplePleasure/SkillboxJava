import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIbutton2 {
    private JPanel rootPanel2;
    private JTextField textField;
    private JButton button2;
    private JTextField textLength;

    public GUIbutton2() {
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int random = (int)Math.round(Math.random()*10);
                textField.setText(Integer.toString(random));
            }
        });
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                button2.setBackground(Color.YELLOW);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String length = Integer.toString(textField.getText().length());
                    textLength.setText(length);
                }
            }
        });
    }

    public JPanel getRootPanel2() {
        return rootPanel2;
    }
}
