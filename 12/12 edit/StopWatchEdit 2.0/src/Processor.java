import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Processor extends Thread{
    JLabel time;
    Long REFERENCE_POINT;

    Processor (JLabel time) {
        this.time = time;
        if (time.getText().length()==0) {
            REFERENCE_POINT = Timestamp.valueOf(LocalDateTime.now()).getTime();
        } else {
            REFERENCE_POINT = Timestamp.valueOf(LocalDateTime.now()).getTime() - (long)(Double.parseDouble(time.getText())*1000);
        }
    }

    @Override
    public void run() {
        for (;;) {
            if (!isInterrupted()) {
                Long currentTime = Timestamp.valueOf(LocalDateTime.now()).getTime();
                double result = (double)(currentTime - REFERENCE_POINT)/1000;
                //SwingUtilities.invokeLater(() -> time.setText(Long.toString(currentTime - REFERENCE_POINT)));
                SwingUtilities.invokeLater(() -> time.setText(Double.toString(result)));
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    interrupt();

                }
            } else {
                break;
            }

        }
    }
}
