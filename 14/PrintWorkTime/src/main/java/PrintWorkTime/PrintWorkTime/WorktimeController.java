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

        Map<Integer, VoteStationWorkTime> m = handler.getMap();


        StringBuilder builder = new StringBuilder();

        builder.append("<table id=\"table\" border=\"1\" bordercolor=\"blue\" cellpadding=\"10\" align=\"center\">" +
                " <tr align=\"center\"><td>station</td><td a>17.09.2015</td><td>19.09.2015</td><td>21.09.2015</td></tr>");
        for(Map.Entry<Integer, VoteStationWorkTime> e : m.entrySet()) {

            builder.append("<tr> <td>" + e.getKey() + "</td>");
            if (e.getValue().periods.size() == 3) {
                for (TimePeriod period : e.getValue().periods) {
                    builder.append("<td>"+ period.toString() + "</td>");
                }
            } else {

                int firstDate =Integer.parseInt(e.getValue().periods.first().getVisitDate().substring(8, 10));
                int lastDate = Integer.parseInt(e.getValue().periods.last().getVisitDate().substring(8, 10));

                if (firstDate ==17 && lastDate == 19) {
                    builder.append("<td>" + e.getValue().periods.first().toString() + "</td><td>" + e.getValue().periods.first().toString() + "</td><td bgcolor=\"green\"></td>" );
                } else if (firstDate ==19 && lastDate == 21) {
                    builder.append("<td bgcolor=\"green\"></td><td>" + e.getValue().periods.first().toString() + "</td><td>" + e.getValue().periods.first().toString() + "</td>" );
                } else if (firstDate ==17 && lastDate == 21) {
                    builder.append("<td>" + e.getValue().periods.first().toString() + "</td><td bgcolor=\"green\"></td><td>" + e.getValue().periods.first().toString() + "</td>" );
                }
            }
            builder.append("</tr>");
        }
        builder.append("</table>");

        return  builder.toString();
    }


}


