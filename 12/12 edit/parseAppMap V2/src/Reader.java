import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Reader extends Thread {


    Screen screen;
    String url;
    ConcurrentHashMap map;

    Reader(Screen screen, String url, ConcurrentHashMap map) {


        this.screen = screen;
        this.url = url;
        this.map = map;
    }


    @Override
    public void run() {
        super.run();

        Thread thread = this;
        screen.addthr(thread);


        Document doc=null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements el = doc.select("a");
        for (Element e: el) {
            if (!thread.isInterrupted() && screen.canCreate) {
                if (e.absUrl("Href").length()>= screen.firstStr.length()
                        &&   e.absUrl("href").substring(0, screen.firstStr.length()).equals(screen.firstStr)) {

                    try {
                        thread.sleep((int)(Math.random()*10));
                    } catch (InterruptedException ex) {
                        thread.interrupt();
                    }

                    ConcurrentHashMap<String, ConcurrentHashMap> mapCreate = new ConcurrentHashMap<>();
                    map.put(e.absUrl("href"), mapCreate);
                    Reader reader = new Reader(screen, e.absUrl("href"), mapCreate);
                    reader.start();
                    screen.count();
                }
            }
        }


//        for (Object UrlString : map.keySet()) {
//            System.out.println("\n\n" + UrlString);
//
//
//            //map.get(UrlString); // Value
//            //Reader r = new Reader(screen, (ConcurrentHashMap) map.get(UrlString));
//            ConcurrentHashMap<String, ConcurrentHashMap> m = (ConcurrentHashMap) map.get(UrlString);
//
//            Document doc = null;
//            try {
//                doc = Jsoup.connect((String) UrlString).get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Elements e = doc.select("a");
//            for (Element element : e) {
//
//                if (!thread.isInterrupted() && screen.canCreate) {
//
//                    if (element.absUrl("href").substring(0, screen.firstStr.length()).equals(screen.firstStr)) {
//                        try {
//                            Thread.sleep((int) (Math.random() * 10));
//                        } catch (InterruptedException ex) {
//                            thread.interrupt();
//                        }
//
//
//                        m.put(element.absUrl("href"), new ConcurrentHashMap());
//                        Reader reader = new Reader(screen, m);
//                        reader.start();
//
//                    }
//
//                }
//            }
//        }
    }

}



