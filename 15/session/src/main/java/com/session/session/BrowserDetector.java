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
                int iter = 0;
                for (Element el : elements) {
                    if (el.id().equals("family")) {
                        String family = el.text().replace("family: ", "")
                                .replaceAll(" ", "");
                        info += family + " ";
                    }
                    if (el.id().equals("major")) {
                        String major = el.text().replace("major: ", "");
                        info+= major + ".";
                    }
                    if (el.id().equals("minor")) {
                        String minor = el.text().replace("minor: ", "");
                        info += minor + ".";
                    }
                    if (el.id().equals("patch")) {
                        String patch = el.text().replace("patch: ", "");
                        info += patch;
                        if (iter == 0) {
                            info +="\t";
                            iter++;
                        }
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
