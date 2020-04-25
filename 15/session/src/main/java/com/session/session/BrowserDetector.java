package com.session.session;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BrowserDetector {
    static String URL = "https://www.whatsmyua.info/";

    public static String getInfo(String userAgent) {

        String info = "";
        try {
            Connection.Response response = Jsoup.connect(URL).data("custom-ua-string", userAgent)
                    .method(Connection.Method.POST).execute();

            if (response.statusCode() > 199 && response.statusCode()<300) {
                Elements elements = response.parse().select("li");
                for (Element el : elements) {
                    if (el.id().equals("family")) {
                        String family = el.text().replace("family: ", "")
                                .replaceAll(" ", "");
                        info += family;
                        break;
                    }
                }
            } else {
                info += "not detected";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }



}
