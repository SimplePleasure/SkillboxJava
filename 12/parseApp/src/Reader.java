import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

public class Reader extends Thread{


    String url;
    Screen screen;
    ArrayList<String> list= new ArrayList<>();

    Reader(String url, Screen screen) {
        this.screen = screen;
        this.url = url;
    }





    @Override
    public void run() {
        super.run();

        Thread thread = this;
        screen.addthr(thread);

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements e = doc.select("a");

        for (Element el : e) {

            if (!isInterrupted()) {
                try {
                    Thread.sleep((int)(Math.random()*10));
                } catch (InterruptedException w) {
                    Thread.currentThread().interrupt();
                    //return;
                }
            }

            if (el.absUrl("href").length() >= url.length() &&
                    el.absUrl("href").substring(0, url.length()).equals(url)) {


                Boolean sucsessAdd = false;
                int listSize = screen.urls.size();

                screen.urls.add(el.absUrl("href"));

                if (listSize < screen.urls.size()) {
                    sucsessAdd = true;

                }

                if(screen.canCreate && sucsessAdd) {
                    new Thread(new Reader(el.absUrl("href"), screen)).start();
                }
            }
        }
    }







}

