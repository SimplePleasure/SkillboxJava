package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class DefaultController {

    static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd.MM");

    @RequestMapping("/date")
    public String index() {
        return (df.format(new Date()));
    }

}
