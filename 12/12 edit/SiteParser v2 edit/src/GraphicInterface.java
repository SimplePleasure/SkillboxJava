import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class GraphicInterface extends Thread{
    private JPanel rootPanel;
    private JTextField urlField;
    private JButton startParsing;
    private JButton stopParsing;
    private JLabel time;
    private JScrollPane scrollPane;
    private JLabel urlsCountLabel;
    private JTextArea resultTextArea;
    private JRadioButton showSaveOptions;
    private JTextField pathToSave;
    private JButton pauseParsing;




    LinkParser linkParser;
    volatile LocalDateTime timeInstanceStart;
    volatile LocalDateTime timeInstancePauseStarted = null;
    volatile boolean isScanning =false;


    public GraphicInterface() {
        startParsing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startParsing();
            }
        });
        stopParsing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopWorking();
            }
        });
        pauseParsing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linkParser.setPause();
                setPauseOrResume();
            }
        });
        showSaveOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                urlField.setVisible(!urlField.isVisible());
                urlsCountLabel.setVisible(!urlsCountLabel.isVisible());
                time.setVisible(!time.isVisible());
                startParsing.setVisible(!startParsing.isVisible());
                stopParsing.setVisible(!stopParsing.isVisible());
                pathToSave.setVisible(!pathToSave.isVisible());
            }
        });
    }

    synchronized void startParsing() {

        linkParser = new LinkParser();
        linkParser.launch(urlField.getText());

        timeInstanceStart = LocalDateTime.now();
        isScanning = true;
        notify();

        startParsing.setEnabled(false);
        stopParsing.setEnabled(true);
    }

    void stopWorking()  {
        isScanning = false;
        stopParsing.setEnabled(false);
        startParsing.setEnabled(true);
        showResult(linkParser.stopParsing());



        if (pathToSave.getText().length()>0) {
//            SaveResults.save(pathToSave.getText(), resultTextArea.getText());
            Path path = Paths.get(pathToSave.getText());
            try {
                Files.writeString(path, resultTextArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void setPauseOrResume() {
        isScanning = !isScanning;
        if (isScanning) {
            timeInstanceStart = timeInstanceStart
                    .plusSeconds(Duration.between(timeInstancePauseStarted,LocalDateTime.now()).getSeconds());
            notify();
        } else {
            timeInstancePauseStarted = LocalDateTime.now();
        }
    }


   //=======================================================

    void showResult(Set<String> urlList) {

        resultTextArea.setText("");
        Stream<String> stream = urlList.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.replaceAll("[^/]", "").compareTo(o2.replaceAll("[^/]", ""));
            }
        });

        stream.map(x -> "\t".repeat(x.replaceAll("[^/]", "").length()-3) + x)
                .map(x -> x + "\n")
                .forEach(resultTextArea::append);
        urlsCountLabel.setText(Integer.toString(urlList.size()));
    }






    @Override
    public void run() {


        for (; ; ) {
            while (!isScanning) {
                synchronized (this) {
                    try {
                        System.out.println("waiting G");
                        wait();
                        System.out.println("working G");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            Duration duration = Duration.between(timeInstanceStart, LocalDateTime.now());
            SwingUtilities.invokeLater(() -> time.setText(Long.toString(duration.toSeconds())));
            showResult(linkParser.showResult());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }





    public JPanel getRootPanel() {
        return rootPanel;
    }







}
