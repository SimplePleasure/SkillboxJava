import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;

public class LinkParser {





    private String rootUrl;
    private volatile boolean isScanning;
    private Set<String> urlsList;
    private ExecutorService executorService;

    private final Object monitor = new Object();
    private volatile boolean isPaused;

    void launch(String url) {
        isScanning = true;
        isPaused = false;
        rootUrl = url;
        urlsList = ConcurrentHashMap.newKeySet();
        executorService = Executors.newFixedThreadPool(2);
        parse(rootUrl);
    }

    //todo set->void
    Set<String> stopParsing() {
        if (isPaused) {
            synchronized (monitor) {
                isPaused=false;
                monitor.notifyAll();
            }
        }
        isScanning = false;
        executorService.shutdown();
        return urlsList;
    }
    Set <String> showResult () {
        return urlsList;
    }


    void setPause() {
        synchronized (monitor) {
            isPaused = !isPaused;
            if (!isPaused) {
                monitor.notifyAll();
            }
        }
    }


    private void parse(String url) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Document jsoupDoc = Jsoup.connect(url).maxBodySize(0).get();
                    Elements selectedItems = jsoupDoc.select("a");
                    for (Element element : selectedItems) {
                        String path = element.absUrl("href");
                        if (path.startsWith(rootUrl)) {
                            if (isScanning) {

                                while (isPaused) {
                                    synchronized (monitor) {
                                        try {
                                            System.out.println("Parser waiting..." + Thread.currentThread().getName());
                                            monitor.wait();
                                            System.out.println("Parser working..." + Thread.currentThread().getName());
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                try {
                                    Thread.sleep((int) (Math.random() * 10));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                urlsList.add(path);
                                parse(path);
                            } else {break;}
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


