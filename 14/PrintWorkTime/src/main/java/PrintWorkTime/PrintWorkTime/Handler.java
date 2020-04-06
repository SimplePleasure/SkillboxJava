package PrintWorkTime.PrintWorkTime;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import java.util.TreeSet;

public class Handler extends DefaultHandler {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    TreeMap<Integer, VoteStationWorkTime> workTimeByStation;
    TreeSet<LocalDate> dayList;
    SAXParser parser;

    Handler(String path) {
        workTimeByStation = new TreeMap<>();
        dayList = new TreeSet<>();

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            parser = spf.newSAXParser();
            parser.parse(path, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("visit")) {
            Integer station = Integer.parseInt(attributes.getValue("station"));
            LocalDateTime visit = LocalDateTime.parse(attributes.getValue("time"), formatter);

            if (!dayList.contains(visit.toLocalDate())) {
                dayList.add(visit.toLocalDate());
                for (VoteStationWorkTime stationWorkTime: workTimeByStation.values()) {
                    stationWorkTime.addNewTimePeriod(visit.toLocalDate());
                }
            }
            VoteStationWorkTime voteStation = workTimeByStation.get(station);
            if (voteStation == null) {
                voteStation = new VoteStationWorkTime(dayList);
                workTimeByStation.put(station, voteStation);
            }
            voteStation.addVisitTime(visit);
        }
    }

    TreeSet<LocalDate> getWorkingDaysList () {
        return dayList;
    }

    TreeMap<Integer, VoteStationWorkTime> getVoteStationByNum() {
        return workTimeByStation;
    }
}
