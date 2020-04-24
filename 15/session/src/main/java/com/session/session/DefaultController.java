package com.session.session;

import com.session.session.Beans.SessionBean;
import com.session.session.model.NoteRepository;
import com.session.session.model.VisitSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class DefaultController {

    @Autowired
    SessionBean session;
    @Autowired
    NoteRepository noteRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam String name, @RequestHeader("user-agent") String info) {
        if (name != null && name.length()>0) {
            session.setName(name);
            String browser = BrowserDetector.getInfo(info);
            noteRepository.save(new VisitSaver(name, browser));
            return "redirect:/";
        }
        return "authorize";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (!StringUtils.isEmpty(session.getName())) {
            ConcurrentLinkedQueue<String> list = session.getStorage().getList();
            model.addAttribute("list", list);
            model.addAttribute("name", session.getName());
            return "index";
        }
        return "authorize";
    }

    @PostMapping("/")
    public String post(@RequestParam(name = "note") String str) {
        session.getStorage().addLine(str);
        return "redirect:/";
    }

}
