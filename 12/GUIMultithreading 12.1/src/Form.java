import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Danya on 28.10.2015.
 */
public class Form
{
    private JPanel rootPanel;
    private JPanel buttons;
    private JButton startButton;
    private JProgressBar progressBar;
    private JButton stopButton;
    private Processor processor;

    public Form() {
        Form form = this;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Два варианта создания потока:
                //1)
                processor = new Processor(form);
                processor.start();


                // 2)
//                Runnable task = new Runnable() {
//                    @Override
//                    public void run() {
//                        startButton.setEnabled(false);
//
//                        int count = 100000000;
//                        for(int i = 0; i < count; i++) {
//                            progressBar.setValue((int) Math.round(i*100./count));
//                        }
//                        startButton.setEnabled(false);
//                    }
//                };
//                Thread calculator = new Thread(task);
//                calculator.start();

            }
        });


        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processor.interrupt();
            }
        });
    }



    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getStartButton () {
        return startButton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
