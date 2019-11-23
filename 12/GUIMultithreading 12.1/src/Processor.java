import javax.swing.*;

public class Processor extends Thread
{
    JProgressBar progressBar;
    JButton button;


    Processor(Form form) {
    progressBar = form.getProgressBar();
    button = form.getStartButton();
    }


    @Override
    public void run() {

        button.setEnabled(false);
        int count = 100000000;
        for(int i = 0; i < count; i++) {

            if (isInterrupted()) {
                progressBar.setValue(0);
                break;
            }
            progressBar.setValue((int) Math.round(i*100./count));
        }
        button.setEnabled(true);

    }
}
