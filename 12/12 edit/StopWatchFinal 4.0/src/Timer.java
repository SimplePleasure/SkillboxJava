import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Timer {
    private JPanel RootPanel;
    private JLabel timeField;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private Processor processor = new Processor(timeField);


    public Timer() {
        timeField.setText("0 : 0");
        processor.setDaemon(true);
        processor.start();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                processor.startOrResume();
                startButton.setVisible(false);
                pauseButton.setVisible(true);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pauseButton.setVisible(false);
                startButton.setVisible(true);
                processor.timePause();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pauseButton.setVisible(false);
                startButton.setVisible(true);
                processor.timeStop();
            }
        });

    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
