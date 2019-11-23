import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Loader {
    public static void main (String[] args) throws IOException {





        String url = "https://course.skillbox.ru/webdev/";
        Document doc = Jsoup.connect(url).get();
        ArrayList<String> imgURL = new ArrayList<>();

        Elements el = doc.select("img");
        for (Element element : el) {
            if (element.attr("data-original").isEmpty()) {
                imgURL.add(element.attr("src"));
            } else {
                imgURL.add(element.attr("data-original"));
            }
        }


        for (String s : imgURL) {
                try (InputStream in = new URL(s).openStream()) {
                    Files.copy(in, Paths.get("C:\\Users\\Костя\\Desktop\\Новая папка\\" + rand() + ".jpg"));
                }
        }




//        for (String s : imgURL) {
//            URL url2 = new URL(s);
//            InputStream fis = url2.openStream();
//            FileOutputStream fos = new FileOutputStream("C:\\Users\\Костя\\Desktop\\Новая папка\\" + rand()+".jpg");
//
//            for (;;) {
//                int code = fis.read();
//                if (code<0) break;
//                fos.write(code);
//
//            }
//            fos.flush();
//            fos.close();
//
//        }



    }




    public static String rand() {
        String random = Double.toString(Math.random()).substring(2);
        return random;
    }




}
