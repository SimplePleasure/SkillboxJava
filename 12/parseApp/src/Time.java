import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class Time extends Thread {
    JLabel time;



    Time (JLabel time) {
        this.time = time;
    }



    @Override
    public void run() {
        super.run();


        for (int s=0;;s++) {


            for (int ms=0; ms<100; ms++) {

                time.setText(s+":"+ms);
                if (!isInterrupted()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }
}
