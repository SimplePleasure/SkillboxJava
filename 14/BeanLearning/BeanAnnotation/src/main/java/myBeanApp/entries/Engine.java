package myBeanApp.entries;

import myBeanApp.Detail;
import org.springframework.stereotype.Component;

@Component
public class Engine implements Detail {

    String engine = "ToyotaMotors";
    int capacity = 2;


    @Override
    public String detail() {
        return "Engine: " + engine + " Capacity: "+ capacity + "\n";
    }
}
