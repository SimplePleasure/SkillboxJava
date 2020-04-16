package PrintWorkTime.PrintWorkTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WorktimeController {


    @RequestMapping(value = "/")
    public String getWorkTime(Model model) {
        Handler handler = new Handler("./src/main/resources/data-0.2M.xml");

        model.addAttribute("voteTable", handler.getVoteStationByNum());
        model.addAttribute("workingDays", handler.getWorkingDaysList());
        return "index.html";
    }


}



