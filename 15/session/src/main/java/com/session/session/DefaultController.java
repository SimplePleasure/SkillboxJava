package com.session.session;

import com.session.session.Beans.SessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
public class DefaultController {

    @Autowired
    SessionBean session;



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam String name) {
        if (name != null && name.length()>5) {
                session.setName(name);
                return "redirect:/";
        }
        return "authorize.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        if (!StringUtils.isEmpty(session.getName())) {
        model.addAttribute("list", session.getStorage().getList());
        model.addAttribute("size", session.getStorage().getSize());
        model.addAttribute("name", session.getName());
        return "index.html";
        }

        return "authorize.html";
    }
    @PostMapping("/")
    public String post(@RequestParam(name = "line") String str, Model model) {

        session.getStorage().addLine(str);
        return "redirect:/";
    }

}
