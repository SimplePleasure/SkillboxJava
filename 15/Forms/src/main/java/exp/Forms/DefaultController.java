package exp.Forms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Controller
public class DefaultController {


    @GetMapping("/")
    public String index(Person p){
        return "index.html";
    }

    @PostMapping("/templates")
    public String test(@Validated @ModelAttribute Person p,
                       BindingResult bindingResult,
                       @RequestParam("scan")MultipartFile file,
                       Model model) {

        System.out.println("****************\nexecuting\n****************");

        if(!file.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream(new File("./src/main/resources/static/screens/"+
                    p.getName()+ p.getPatronymic() + " " + file.getOriginalFilename())))   {
                fos.write(file.getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("scan", p.getName()+ p.getPatronymic() + " " + file.getOriginalFilename());
        }


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
