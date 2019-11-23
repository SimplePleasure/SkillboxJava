import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Timer {
    private JPanel RootPanel;
    private JLabel time;
    private JButton stopButton;
    private JButton startButton;
    private JButton pause;
    private JButton begin;
    private Thread thread;


    private String[] pauseTime;
    int m;
    int s;
    int ms;


    Timer() {
        RootPanel.setLayout(new BoxLayout(RootPanel, BoxLayout.Y_AXIS));


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                thread = new Processor(time);


                thread.start();
                startButton.setVisible(false);
                pause.setVisible(true);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread.interrupt();

                time.setText("0:0:0");
                pause.setVisible(false);
                begin.setVisible(false);
                startButton.setVisible(true);


            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                pauseTime = time.getText().split(":");


                thread.interrupt();
                pause.setVisible(false);
                begin.setVisible(true);
            }
        });

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int m = Integer.parseInt(pauseTime[0]);
                int s = Integer.parseInt(pauseTime[1]);
                int ms = Integer.parseInt(pauseTime[2]);


                thread = new Processor(time, m, s, ms);
                begin.setVisible(false);
                pause.setVisible(true);
                thread.start();

            }
        });
    }






    public JPanel getRootPanel() {
        return RootPanel;
    }

}
