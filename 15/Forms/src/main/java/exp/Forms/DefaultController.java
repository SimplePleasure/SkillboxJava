package exp.Forms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class DefaultController {

    @PostMapping("/templates")
    public String test(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic,
                       @RequestParam String birthday, @RequestParam String phone, @RequestParam Integer period,
                       @RequestParam Integer count, Model model) {


        System.out.println( name + "\n" + surname + "\n" + patronymic + "\n" + birthday + "\n" + phone + "\n" + period + "\n" + count);
        return "filledForm.html";
    }

}
