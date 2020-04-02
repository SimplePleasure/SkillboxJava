package myBeanApp.entries;

import myBeanApp.Detail;
import org.springframework.stereotype.Component;

@Component
public class Transmission implements Detail {

    String type = "auto";

    @Override
    public String detail() {
        return "Transmission: " + type + "\n";
    }
}
