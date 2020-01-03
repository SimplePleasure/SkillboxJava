import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;


public class MainForm {
    private JPanel rootPanel;
    private JPanel scrollPanel;
    private JPanel pathField;
    private JScrollPane scroll;
    private JTextArea textArea;
    private JTextField path;
    private JButton loadFile;


    int knobSize;
    Storage cs;

    MainForm() {
//      ===================== Painting =================================================================================
        scrollPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                scrollPanel.repaint();
                scrollPanel.revalidate();
            }
        });
        scrollPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                System.out.println(e.getY() + "\t" + e.getClickCount());
                Graphics g = scrollPanel.getGraphics();
                g.setColor(new Color(154, 67, 255, 100));
                g.fillRect(0, e.getY(), 20, knobSize);

                double percent = (double)e.getY()/scrollPanel.getHeight();
                cs.shiftToPercent(percent);
                textArea.setText(cs.getChunk().getText());
            }
        });




//      ============================================================= Loading / scrolling ==============================
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cs != null) cs.close();
                cs = new Storage(path.getText());
                knobSize = (scrollPanel.getHeight() / cs.getChunkCount()) > 5 ?
                        (int) (scrollPanel.getHeight() / cs.getChunkCount()) : 5;
                textArea.setText(cs.getChunk().getText());
            }
        });

        scroll.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                if(e.getWheelRotation() == 1 && scroll.getVerticalScrollBar().getMaximum() -
                        scroll.getVerticalScrollBar().getVisibleAmount() == scroll.getVerticalScrollBar().getValue()) {
                    textArea.setText(cs.getChunk().getText());
                } else if (e.getWheelRotation() == -1 && scroll.getVerticalScrollBar().getValue() == 0) {
                    cs.shiftBackward();
                    textArea.setText(cs.getChunk().getText());
                }

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        scrollPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                g.setColor(new Color(154, 67, 255));
                g.drawRect(0,0, getWidth()-1, getHeight()-2);
                //Arrow up
                g.drawLine(10, 2, 10, 12);
                g.drawLine(11, 2, 11, 12);
                g.drawLine(10, 2, 5, 6);
                g.drawLine(11, 2, 16, 6);
                //Arrow down
                g.drawLine(10, getHeight()-4, 10, getHeight()-14);
                g.drawLine(11, getHeight()-4, 11, getHeight()-14);
                g.drawLine(10, getHeight()-4, 5, getHeight()-8);
                g.drawLine(11, getHeight()-4, 16, getHeight()-8);
            }
        };
    }
}
