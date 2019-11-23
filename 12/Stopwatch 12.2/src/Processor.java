import javax.swing.*;

public class Processor extends Thread {
    static JLabel time;
    int m=0;
    int s = 0;
    int ms = 0;



    Processor(JLabel time) {
        this.time = time;
    }

    Processor(JLabel time, int m, int s, int ms) {
        this.time = time;
        this.m = m;
        this.s = s;
        this.ms = ms;

    }





    @Override
    public void run() {
        super.run();

        for (int m=this.m ;; m++) {
            for (int s=this.s; s<60; s++) {
                for (int ms=this.ms; ms<100 ; ms++) {

                    if (!isInterrupted()){
                        String str = m + ":" + s + ":" + ms;
                        //time.setText(str);
                        SwingUtilities.invokeLater(()-> time.setText(str));
                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }





            }
        }
    }



}
