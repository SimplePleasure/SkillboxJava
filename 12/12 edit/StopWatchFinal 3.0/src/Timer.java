import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    private JPanel RootPanel;
    private JLabel timeField;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private Processor processor = new Processor(timeField);


    public Timer() {
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

//                Thread thPause = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        processor.timePause();
//                    }
//                });
//                thPause.setDaemon(true);
//                thPause.start();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pauseButton.setVisible(false);
                startButton.setVisible(true);
                processor.timeStop();

//                Thread thStop = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        processor.timeStop();
//                    }
//                });
//                thStop.setDaemon(true);
//                thStop.start();
            }
        });
    }

    public JPanel getRootPanel() {
        return RootPanel;

    }
}
