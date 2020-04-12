package exp.Forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @DeleteMapping("/up/{fileName}")
    public ResponseEntity del(@PathVariable String fileName) {

        boolean result = storage.delFile(fileName);
        if (result) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }


//
//    @GetMapping("/")
//    public String test(){
//        return "index.html";
//    }
//    @PostMapping("/")
//    @ResponseBody
//    public ResponseEntity upload(@RequestParam("file") MultipartFile f){
//
//        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "attachment; filename=\"" + f.getOriginalFilename() + "\"")
//                .body(new ModelAndView("index.html", HttpStatus.OK));
//    }
//    @GetMapping("/test")
//
//    public String test1(){
//        return "<title>index</title><h1>testetstest</h1>";
//    }

}