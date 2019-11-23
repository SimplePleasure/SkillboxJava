import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class Loader {

    public static void main (String[] args) throws IOException {
        int i=0;
        String path = "C:\\Users\\Костя\\Desktop\\Projects\\10\\ParseHTML\\res\\Веб-разработчик c 0 до PRO.html";
        Document doc = Jsoup.parse(new File(path), "UTF-8");
        Elements elements = doc.select("img");
        for (Element e : elements) {
            if (!e.attr("src").isEmpty()){
                System.out.println(e.attr("src"));
                i++;
            }else if (!e.attr("data-original").isEmpty()){
                System.out.println(e.attr("data-original"));
                i++;
            }
        }
        System.out.println("Тегов img отсканирокано:\t" + elements.size());

        elements = doc.select("div");

        for (Element e : elements) {
            if (!e.attr("data-original").isEmpty()) {
                System.out.println(e.attr("data-original"));
                i++;
            } else if (!e.attr("data-content-cover-bg").isEmpty()) {
                System.out.println(e.attr("data-content-cover-bg"));
                i++;
            }
        }
        System.out.println("Тегов div отсканирокано:\t" + elements.size() +"\n Ссылок на изображения распечатано: \t" + i);






        System.out.println("\n\n\n");
        Document doc2 = Jsoup.connect("http://ucancode.ru/java/").get();
        Elements newsHeadlines = doc2.select("img");
        for (Element headline : newsHeadlines) {
            if (!headline.attr("src").isEmpty()) {
                System.out.println(headline.attr("src"));
            } else {
                System.out.println(headline.attr("data-original"));
            }
        }
        System.out.println("Последний блок со ссылками распечатаны без загрузки страницы на компьютер");



    }

}






//<link href="https://static.tildacdn.com/tild3735-6539-4431-a638-663530323539/favicon.ico" >
