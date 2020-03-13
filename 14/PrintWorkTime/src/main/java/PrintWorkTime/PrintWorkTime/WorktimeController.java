package PrintWorkTime.PrintWorkTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Map;

@RestController
public class WorktimeController {

    @RequestMapping(value = "/")
    public String getWorkTime() {

        Handler handler = new Handler();
        try {
            File f= new File("./src/main/resources/data-0.2M.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            parser.parse(f, handler);
        } catch (Exception e) {}


//                                                                       Preparing TABLE
        Map<Integer, VoteStationWorkTime> m = handler.getMap();

        StringBuilder builder = new StringBuilder();
        builder.append("<table id=\"table\" border=\"1\" bordercolor=\"blue\" cellpadding=\"10\" align=\"center\">" +
                " <tr align=\"center\"><td>station</td><td a>17.09.2015</td><td>19.09.2015</td><td>21.09.2015</td></tr>");
        for(Map.Entry<Integer, VoteStationWorkTime> e : m.entrySet()) {
            builder.append("<tr> <td>" + e.getKey() + "</td>");
            int row = 0;
            for (TimePeriod period : e.getValue().periods) {
                row ++;

                if (row ==1 && !period.getVisitDate().substring(8,10).equals("17")) {
                    builder.append("<td bgcolor =\"blue\"></td>");
                    row++;
                    builder.append("<td>" + period.toString() + "</td>");
                    continue;
                } else if (row == 2 && !period.getVisitDate().substring(8,10).equals("19")) {
                    builder.append("<td bgcolor =\"blue\"></td>");
                    row++;
                    builder.append("<td>" + period.toString() + "</td>");
                    continue;
                }
                builder.append("<td>" + period.toString() + "</td>");
                if (row==2 && e.getValue().periods.size()==2) builder.append("<td bgcolor =\"blue\"></td>");
            }
            builder.append("</tr>");
        }
        builder.append("</table>");

        return  builder.toString();
    }


}



