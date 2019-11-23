import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataPrepare {

    private String rootUrl;
    private volatile boolean isScanning;
    private Set<String> urlsList;
    private ExecutorService executorService;

    private LocalDateTime localDateTime;
    private JLabel timeLabel;


    void launch(String url, JLabel time) {
        isScanning = true;
        rootUrl = url;
        timeLabel = time;
        localDateTime = LocalDateTime.now();
        startParse();

    }

    void stopParsing(JTextArea textArea, JLabel resultLabel, JTextField pathToSave) {
        isScanning = false;
        executorService.shutdown();

        showResult(textArea, resultLabel, pathToSave);
    }


    //=============================================================
    private void showResult(JTextArea textArea, JLabel resultLabel, JTextField pathToSave) {
        textArea.setText("");
        int count=0;
        for (String path : urlsList) {
            textArea.append(path + "\n");
            count++;
        }
        final int resultCount = count;
        SwingUtilities.invokeLater( () -> resultLabel.setText(Integer.toString(resultCount)));


        if (pathToSave.getText().trim().length() > 0) {
            SaveResults save = new SaveResults(textArea.getText(), pathToSave.getText());
            save.save();
        }

    }


    private void startParse() {



        Thread watch = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isScanning) {
                    Duration period = Duration.between(localDateTime, LocalDateTime.now());
                    SwingUtilities.invokeLater(() -> timeLabel.setText(Long.toString(period.toSeconds())));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        watch.setDaemon(true);
        watch.start();



        urlsList = ConcurrentHashMap.newKeySet();
        executorService = Executors.newFixedThreadPool(2);
        parse(rootUrl);

    }


    private void parse(String url) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document jsoupDoc = Jsoup.connect(url).maxBodySize(0).get();
                    Elements selectedItems = jsoupDoc.select("a");
                    for (Element element : selectedItems) {
                        String path = element.absUrl("href");
                        if (path.startsWith(rootUrl)) {
                            if (isScanning) {

                                try {
                                    Thread.sleep((int) (Math.random() * 10));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                System.out.println(Thread.currentThread().getName() + "\t" + path);
                                urlsList.add(path);
                                parse(path);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


