package exp.Forms;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class DefaultController {

    @RequestMapping("/date")
    public String test() {
       return "index.html";
    }

}
