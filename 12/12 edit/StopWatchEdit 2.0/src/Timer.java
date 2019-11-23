import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    private JPanel RootPanel;
    private JButton Pause;
    private JButton Start;
    private JButton Stop;
    private JButton pause;
    private JLabel time;

    Processor processor;

    public Timer() {
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processor = new Processor(time);
                processor.start();
                Start.setVisible(false);
                Pause.setVisible(true);

            }
        });
        Pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processor.interrupt();
                Pause.setVisible(false);
                Start.setVisible(true);
            }
        });
        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processor.interrupt();
                time.setText("");
                Pause.setVisible(false);
                Start.setVisible(true);
            }
        });
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
