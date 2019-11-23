import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    private JButton button1;
    private JTextField pathToSaveResults;


    protected Vector<Thread> threads = new Vector<>();
    ConcurrentHashMap <String, ConcurrentHashMap> urlMap;
    Reader reader;
    Screen screen;
    String firstStr;
    public Boolean canCreate=true;

    int urlCounter;
    int layer = 0;
    String symbol = "\n";





    public Screen() {
        screen = this;


        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                urlList.setText("");
                Start.setEnabled(false);
                Stop.setEnabled(true);
                result.setVisible(false);
                thcount.setVisible(true);
                hint.setVisible(true);



                canCreate = true;
                urlCounter=0;
                firstStr = Url.getText();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        thcount.setVisible(true);

                        for (;;) {

                            if (!canCreate && Thread.getAllStackTraces().keySet().size()>18) {
                                Url.setVisible(false);
                                progressBar.setVisible(true);
                                progressBar.setValue(Thread.getAllStackTraces().keySet().size()*-1);

                            }
                            if (!canCreate && Thread.getAllStackTraces().keySet().size()<18) {
                                progressBar.setVisible(false);
                                Url.setVisible(true);

                                thcount.setVisible(false);
                                hint.setVisible(false);
                                result.setText("Result: "+ urlCounter);
                                result.setVisible(true);

                                Thread th = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        isThreadsStopped(urlMap);
                                    }
                                });
                                th.start();
                                try {
                                    th.join();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }

                                String siteMap = urlList.getText();
                                //System.out.println(siteMap);
                                SaveResults sr = new SaveResults(siteMap, pathToSaveResults);
                                sr.start();



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

                Time stopWatch = new Time(time);
                threads.add(stopWatch);
                stopWatch.start();



                urlMap = new ConcurrentHashMap<>();
                ConcurrentHashMap <String, ConcurrentHashMap> secondMap = new ConcurrentHashMap<>();
                urlMap.put(firstStr, secondMap);
                reader = new Reader(screen, Url.getText(), secondMap);
                reader.start();



            }
        });


        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canCreate = false;
                for (Thread t: threads) {
                    t.interrupt();
                }
                Start.setEnabled(true);
                progressBar.setMinimum(Thread.getAllStackTraces().keySet().size()*-1);
                if (!canCreate && Start.isEnabled()) {
                    Stop.setEnabled(false);
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlList.setText("");
                if (hint.isVisible()) {
                    time.setVisible(!time.isVisible());
                    Stop.setVisible(!Stop.isVisible());
                    Start.setVisible(!Start.isVisible());
                    Url.setVisible(!Url.isVisible());
                    hint.setVisible(!hint.isVisible());
                    thcount.setVisible(!thcount.isVisible());
                    pathToSaveResults.setVisible(!pathToSaveResults.isVisible());
                } else {
                    result.setVisible(!result.isVisible());
                    Url.setVisible(!Url.isVisible());
                    time.setVisible(!time.isVisible());
                    Stop.setVisible(!Stop.isVisible());
                    Start.setVisible(!Start.isVisible());
                    pathToSaveResults.setVisible(!pathToSaveResults.isVisible());

                }

            }
        });
    }



    synchronized protected void count(){
        urlCounter++;
    }


    synchronized protected void addthr (Thread thread) {
        threads.add(thread);
    }

    protected void isThreadsStopped(ConcurrentHashMap map) {

//        System.out.println(map.size());
//        if (map.size()>0) {
//            for (Object url : map.keySet()) {
//                String newurl = (String) url;
//                urlList.append(newurl + "\n");
//               // isThreadsStopped((ConcurrentHashMap) map.get(url));
//            }
//        }


        for(Object s: map.keySet()){


            urlList.append(getTab()+s+"\n");
            ConcurrentHashMap inmap =(ConcurrentHashMap) map.get(s);
            if (inmap.size()>0) {
                layer++;
                isThreadsStopped(inmap);
            }
        }
        layer--;

    }
    String getTab(){
        String str = "";
        for (int i = 0; i < layer; i++) {
            str += "\t";
        }
        return str;
    }























    public JPanel getRootPanel() {
        return RootPanel;
    }

}




