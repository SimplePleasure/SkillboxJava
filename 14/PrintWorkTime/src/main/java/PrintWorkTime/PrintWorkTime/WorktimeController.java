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


    static LocalDate dayOne = LocalDate.parse("2015.09.17", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    static LocalDate dayTwo = LocalDate.parse("2015.09.19", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    static LocalDate dayThree = LocalDate.parse("2015.09.21", DateTimeFormatter.ofPattern("yyyy.MM.dd"));

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

        Map <Integer, VoteStationWorkTime> m = handler.getMap();
        model.addAttribute("voteTable", m);

        model.addAttribute("one", dayOne);
        model.addAttribute("two", dayTwo);
        model.addAttribute("three", dayThree);

        return "index.html";
    }


}



