import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Output {
    private JPanel rootPanel;
    private JTextArea textArea;
    private JButton next;
    Handler handler;


    public Output() {

        textArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                System.out.println("wheel");
//                for (Object v : handler.next().keySet()) {
//                    textArea.append(v.toString() + "\n");
//                }
            }
        });
    }



    void statrReading() throws Exception {

        String fileName = "res/data-0.2M.xml";
        handler = new Handler();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        parser.parse(fileName, handler);

    }




    public JPanel getRootPanel() {
        return rootPanel;
    }
}
