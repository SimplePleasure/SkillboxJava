import javax.swing.*;

public class Time extends Thread {
    JLabel time;



    Time (JLabel time) {
        this.time = time;
    }

    @Override
    public void run() {
        super.run();


        for (int s=0;;s++) {


            for (int ms=0; ms<10; ms++) {

                time.setText(s+":"+ms);
                if (!isInterrupted()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    break;
                }
            }
            if (isInterrupted()) {break;}
        }
    }
}
