package exp.Forms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {


    @ModelAttribute
    public void attr(Model model){
        model.addAttribute("key", "value");
    }


    @GetMapping("/")
    public String index(){
        return "index.html";
    }


    @PostMapping("/templates")
    public String test(@Validated @ModelAttribute Person p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Form is not filled");
            return "index.html";
        }
        return "filledForm.html";
    }


//    @PostMapping("/templates")
//    public String test(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic,
//                       @RequestParam String birthday, @RequestParam String phone, @RequestParam Integer period,
//                       @RequestParam Integer count) {
//
//        return "filledForm.html";
//    }
}
