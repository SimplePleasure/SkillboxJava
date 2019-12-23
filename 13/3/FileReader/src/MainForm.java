import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
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



//        reader = new FileReader("C:\\Users\\Костя\\Desktop\\Projects\\13\\FileReader\\res\\data-18M.xml");
//        reader = new FileReader("C:/Users/Костя/Desktop/Projects/13/FileReader/res/data-18M.xml");


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reader = new FileReader("res/" + pathField.getText());
                position = reader.getPosition();
                System.out.println(position);
            }
        });

        scroll.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                int maxScroll = scroll.getVerticalScrollBar().getMaximum()- 528;   //??
                System.out.println("maxScroll: " + maxScroll);
                System.out.println("value: " + scroll.getVerticalScrollBar().getValue());
                if (scroll.getVerticalScrollBar().getValue()  >= maxScroll && e.getWheelRotation()== 1 && e.isShiftDown()) {
                    System.out.println("length: " + position);
                    showNext();
                }
                if (scroll.getVerticalScrollBar().getValue()  == 0 && e.getWheelRotation()== -1 && e.isShiftDown()) {
                    position = position-5000;
                    if (position <= 0) {
                        position = 0;
                        showNext();
                    } else {
                        position = reader.shiftLine(position);
                        showNext();
                    }
                }
            }
        });
    }

    void showNext() {
        try {
            String res = reader.read(position).toString();
            String utf8 = new String(res.getBytes("ISO-8859-1"), "UTF-8");
            textArea.setText("\n"+utf8+"\n");
            position = reader.getPosition();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
