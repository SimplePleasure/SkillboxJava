package com.session.session;

import com.session.session.Beans.SessionBean;
import com.session.session.model.NoteRepository;
import com.session.session.model.VisitSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Queue;

@Controller
public class DefaultController {


    SessionBean session;
    NoteRepository noteRepository;


//    Инжектить лучше через конструктор а не сразу в поля
    DefaultController(@Autowired SessionBean sessionBean,
                      @Autowired NoteRepository repository) {
        session = sessionBean;
        noteRepository = repository;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam String name, @RequestHeader("user-agent") String info) {
        if (name != null && name.length()>0) {
            String browser = BrowserDetector.getInfo(info);
            noteRepository.save(new VisitSaver(name, browser));
            session.setName(name);
            session.setStatistic(CountStatistics.getStat(noteRepository.getUses()));
            return "redirect:/";
        }
        return "authorize";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        if (!StringUtils.isEmpty(session.getName())) {
            Queue<String> list = session.getStorage();
            model.addAttribute("list", list);
            model.addAttribute("name", session.getName());
            model.addAttribute("statistics", session.getStatistic());
            return "index";
        }
        return "authorize";
    }

    @PostMapping("/")
    public String post(@RequestParam(name = "note") String str) {
        session.getStorage().add(str);
        return "redirect:/";
    }

}
