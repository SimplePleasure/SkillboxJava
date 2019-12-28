import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.UnsupportedEncodingException;

public class MainForm {

    private JPanel rootPanel;
    private JTextArea textArea;
    private JButton button1;
    private JScrollPane scroll;
    private JTextField pathField;
    FileReader reader;
    long position = 0;


    MainForm(){
                button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (reader != null) reader.close();
                reader = new FileReader(pathField.getText());
                position = reader.getPosition();
                System.out.println(position);
            }
        });

        scroll.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar vScroll = scroll.getVerticalScrollBar();
                int maxScroll = vScroll.getMaximum() - vScroll.getVisibleAmount();
                int current = vScroll.getValue();
//                System.out.println("maxScroll: " + maxScroll);
//                System.out.println("value: " + current);
                if (current == maxScroll && e.getWheelRotation()== 1 && e.isShiftDown()) {
                    showNext();
                }
                if (current == 0 && e.getWheelRotation()== -1 && e.isShiftDown()) {
                    position = reader.shiftLine(position-5000);
                    showNext();
                }
            }
        });
    }

    void showNext() {
        try {
            String res = reader.read(position).toString();
            String utf8 = new String(res.getBytes("ISO-8859-1"), "UTF-8");
            textArea.setText(utf8);
            position = reader.getPosition();
            System.out.println(utf8.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
