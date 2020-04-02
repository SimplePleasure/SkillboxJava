package myBeanApp.entries;

import myBeanApp.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Wheel implements Detail {

    @Value("${wheel.mark}")
    String name;
    @Value("${wheel.radius}")
    String radius;


    @Override
    public String detail() {
        return "Wheels: " + name + " " + radius + "\n";
    }
}
