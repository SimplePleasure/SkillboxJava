package exp.Forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class DefaultController {

    @Autowired
    FileStorage storage;
    static String path = "/Users/simplepleasure/Doc/SkillboxJava/15/Upload Files/src/main/resources/static/pics/";

    @GetMapping("/up")
    public String index(Model model) {
        model.addAttribute("img", storage.getFiles());
        return "uploadTest.html";
    }

    @PostMapping("/up")
    public ModelAndView fileUpload(@RequestParam("scan") MultipartFile file, Model model) {

        if (!file.isEmpty()) {
            storage.saveFile(file);
            model.addAttribute("img", storage.getFiles());
            return new ModelAndView("uploadTest.html", HttpStatus.OK);
        }
        return new ModelAndView("uploadTest.html", HttpStatus.BAD_REQUEST);
    }

}