package PrintWorkTime.PrintWorkTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class WorktimeController {


    @RequestMapping(value = "/")
    public String getWorkTime(Model model) {

        Handler handler = new Handler();
        try {
            File f= new File("./src/main/resources/data-0.2M.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            parser.parse(f, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("voteTable", handler.getVoteStationByNum());
        model.addAttribute("workingDays", handler.getWorkingDaysList());
        return "index.html";
    }


}



