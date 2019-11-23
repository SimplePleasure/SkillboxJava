import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

public class Screen {


    private JPanel RootPanel;
    private JTextField Url;
    private JButton Stop;
    private JButton Start;
    private JTextArea urlList;
    private JLabel time;
    private JLabel thcount;
    private JLabel hint;
    private JLabel result;
    private JProgressBar progressBar;
    public Boolean canCreate=true;
    private Screen screen;
    private Thread th;
    private int urlsCount =0;
    private TreeSet<String> collection;




    protected Vector<Thread> threads = new Vector<>();

    public CopyOnWriteArraySet<String> urls =new CopyOnWriteArraySet<>();


    public Screen() {



        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                urlsCount=0;
                urls.clear();


                hint.setVisible(true);
                result.setVisible(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i=0;;i++) {
                            if ((!canCreate && Integer.parseInt(thcount.getText())<18)){
                                //  ||
                                //                                    (i%5==0 && Integer.parseInt(thcount.getText())<18))
                                isThreadsstopped();
                                break;
                            }
                            thcount.setText(Integer.toString(Thread.getAllStackTraces().keySet().size()));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }
                }).start();



                Stop.setEnabled(true);

                canCreate = true;
                Thread timeGo = new Thread(new Time(time));
                threads.add(timeGo);
                timeGo.start();
                searsh();
                Start.setEnabled(false);
                urlList.append( "=================================================================================\n");




            }
        });
        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stop();
                canCreate = false;
                for (Thread t : threads) {
                        t.interrupt();
                }

                Start.setEnabled(true);
                if (Start.isEnabled()) {
                    Stop.setEnabled(false);
                }


                //==============================================
                //JOptionPane.showMessageDialog(RootPanel, "Building UrlList...");


                new Thread(new Runnable() {
                    @Override
                    public void run() {



                        Url.setVisible(false);
                        progressBar.setVisible(true);
                        progressBar.setMinimum(Thread.getAllStackTraces().keySet().size() * -1);
                        for (;;) {
                            progressBar.setValue((Thread.getAllStackTraces().keySet().size() * -1));
                            if (Thread.getAllStackTraces().keySet().size() * -1 > -20) break;
                        }
                        Url.setVisible(true);
                        progressBar.setVisible(false);

                    }
                }).start();

                //===============================================





            }
        });
    }


    private void searsh(){
        screen = this;
        th = new Reader(Url.getText(), screen);
        addthr(th);
        th.start();
    }

    protected void isThreadsstopped() {



        Object[] a = urls.toArray();
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
           urlList.append((String) a[i] + "\n");
           urlsCount++;
        }


        hint.setVisible(false);
        result.setVisible(true);
        thcount.setText(Integer.toString(urlsCount));
    }


    synchronized protected void addthr (Thread thread) {
        threads.add(thread);
    }


    public JPanel getRootPanel() {
        return RootPanel;
    }

}
