import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Output {
    private JPanel rootPanel;
    private JTextArea textArea;
    private JButton next;

    Lock lock;
    Condition condition;

    public Output(Lock l, Condition c, boolean isScaning) {

        lock = l;
        condition = c;

        textArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
//                if (e.getWheelRotation() < 0) {
//                    System.out.println("Up");
//                } else {
//                    System.out.println("Down");
//                }
                lock.lock();
                try {
                    isScaning = true;
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
    }

    void waiter() {

    }


    public JPanel getRootPanel() {
        return rootPanel;
    }
}
