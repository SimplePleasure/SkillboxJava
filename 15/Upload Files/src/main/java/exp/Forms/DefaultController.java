package exp.Forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DefaultController {

    @Autowired
    FileStorage storage;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/up";
    }

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

    @DeleteMapping("/up/{fileName}")
    public ResponseEntity del(@PathVariable String fileName) {
        boolean result = storage.delFile(fileName);
        if (result) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }


}