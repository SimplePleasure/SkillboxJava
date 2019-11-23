import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor extends Thread{
    JLabel time;
    boolean isKilled = false;
    boolean isRunning = false;
    LocalDateTime start;
    LocalDateTime pauseStarted = null;

    Processor (JLabel time) {
        this.time = time;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                if (isRunning && pauseStarted == null) {
                    SwingUtilities.invokeLater(() -> time.setText(Duration.between(start, LocalDateTime.now()).toString()));
                    Thread.sleep(100);
                } else {
                    wait();
                }
            } catch (InterruptedException e) {
                interrupted();
            }
        }
    }

    public synchronized void timerDestroy() {
        isKilled = true;
        notify();
    }

    public void timerPause() {
        pauseStarted = LocalDateTime.now();
    }

    public synchronized void timerStartOrResume() {
        if (pauseStarted != null) {
            Duration pauseDuration = Duration.between(pauseStarted, LocalDateTime.now());
            start = start.plus(pauseDuration);
            pauseStarted = null;
            notify();
        } else if (!isRunning) {
            isRunning = true;
            start = LocalDateTime.now();
            notify();
        }
    }

    public void timerStop() {
        isRunning = false;
        pauseStarted = null;
    }
}
