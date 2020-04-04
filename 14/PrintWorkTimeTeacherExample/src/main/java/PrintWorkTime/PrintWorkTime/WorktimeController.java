package PrintWorkTime.PrintWorkTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WorktimeController {


    static LocalDate dayOne = LocalDate.parse("2015.09.17", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    static LocalDate dayTwo = LocalDate.parse("2015.09.19", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    static LocalDate dayThree = LocalDate.parse("2015.09.21", DateTimeFormatter.ofPattern("yyyy.MM.dd"));


    static Random random = new Random();

    public static LocalTime randomTime() {
        return LocalTime.of(random.nextInt(10) + 12, random.nextInt(60));
    }

    public static LocalDate randomDate(int range) {
        return LocalDate.of(2015, 5, random.nextInt(range) + 1);

    }

    @RequestMapping(value = "/")
    public String getWorkTime(Model model) {

        Handler handler = new Handler();
        try {
            File f = new File("./src/main/resources/data-0.2M.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            parser.parse(f, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<Integer, List<TimePeriod>> m = generateRandomStations(20, 10);


        List<LocalDate> columns = m.values().stream()
                .flatMap(Collection::stream)
                .map(TimePeriod::getVisitDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());


        Map<Integer, List<TimePeriod>> rows = new LinkedHashMap<>();

        for (Map.Entry<Integer, List<TimePeriod>> entry : m.entrySet()) {

            var periods = new ArrayList<TimePeriod>();

            for (LocalDate columnDate : columns) {
                periods.add(entry.getValue().stream().filter(v -> v.getVisitDate().equals(columnDate)).findAny().orElse(null));
            }

            rows.put(entry.getKey(), periods);
        }

        model.addAttribute("columns", columns);
        model.addAttribute("rows", rows);

        return "index.html";
    }

    private Map<Integer, List<TimePeriod>> generateRandomStations(int countStations, int daysRange) {
        Map<Integer, List<TimePeriod>> m = new LinkedHashMap<>();

        for (int stationId = 0; stationId < countStations; stationId++) {

            var stationVisits = new ArrayList<TimePeriod>();

            int howManyDaysStationIsOpen = random.nextInt(4) + 4;
            while (howManyDaysStationIsOpen-- > 0) {
                TimePeriod dailyVisits = new TimePeriod(randomDate(daysRange));

                int visitsPerDay = random.nextInt(25) + 5;
                while (visitsPerDay-- > 0) {
                    dailyVisits.addTime(randomTime());
                }

                stationVisits.add(dailyVisits);
            }
            m.put(stationId, stationVisits);
        }
        return m;
    }


}



