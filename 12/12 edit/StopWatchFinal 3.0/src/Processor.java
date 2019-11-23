import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class Processor extends Thread {

    private JLabel timeField;
    protected final Processor lock = this;
    protected boolean stopWatchStarted = false;
    protected volatile LocalDateTime timerStarted;
    protected volatile Duration period;


    Processor(JLabel timeField) {
        this.timeField = timeField;

    }

    @Override
     synchronized public void run() {
        super.run();
        for (; ; ) {
            //synchronized (lock) {
                if (!stopWatchStarted) {    // Если таймер не запущен - поток спит
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                period = Duration.between(timerStarted, LocalDateTime.now());

                if ((period.toMillis()%1000)> 99) {
                    SwingUtilities.invokeLater(() -> timeField.setText(period.toSeconds() + " : "
                            + period.toMillis() % 1000));
                } else if (period.toMillis()%1000 > 9) {
                    SwingUtilities.invokeLater(() -> timeField.setText(period.toSeconds() + " : "
                            +0+ period.toMillis() % 1000));
                } else {
                    SwingUtilities.invokeLater(() -> timeField.setText(period.toSeconds() + " : "
                            +00+ period.toMillis() % 1000));
                }


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //}
        }
    }

    synchronized protected void startOrResume() {
            if (timerStarted == null) {
                stopWatchStarted = true;
                timerStarted = LocalDateTime.now();
                lock.notify();
            } else {
                stopWatchStarted = true;
                timerStarted = LocalDateTime.now().minusNanos(period.toNanos());
                lock.notify();
            }
    }

    protected void timePause() {
        System.out.println("stopwatchh Started = False");
        stopWatchStarted = false;
    }

    protected void timeStop() {
        stopWatchStarted = false;
        timerStarted = null;
        SwingUtilities.invokeLater(() -> timeField.setText("0 : 0"));
    }
}
