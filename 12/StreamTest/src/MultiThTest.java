import java.time.Duration;
import java.time.LocalDateTime;

public class MultiThTest extends Thread {

    volatile boolean isStarted = false;
    LocalDateTime startWatch;



    synchronized void startOrResume () {

        startWatch = LocalDateTime.now();
        isStarted = true;
        notify();
    }
    void pause () {
        isStarted = false;
    }
    @Override
    synchronized public void run() {


        for (;;) {

            while (!isStarted) {
                try {
                    System.out.println("Waiting");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Duration duration = Duration.between(LocalDateTime.now(), startWatch);
            System.out.println(duration.toSeconds());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
