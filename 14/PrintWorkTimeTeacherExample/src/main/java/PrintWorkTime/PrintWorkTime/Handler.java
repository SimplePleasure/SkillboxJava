package PrintWorkTime.PrintWorkTime;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

public class Handler extends DefaultHandler {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    TreeMap<Integer, VoteStationWorkTime> workTimeStorage = new TreeMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("visit")) {
            Integer station = Integer.parseInt(attributes.getValue("station"));
            LocalDateTime visit = LocalDateTime.parse(attributes.getValue("time"), formatter);

            VoteStationWorkTime voteStation = workTimeStorage.get(station);
            if (voteStation == null) {
                voteStation = new VoteStationWorkTime();
                workTimeStorage.put(station, voteStation);
            }
            voteStation.addVisitTime(visit);

        }
    }

    TreeMap<Integer, VoteStationWorkTime> getMap() {
        return workTimeStorage;
    }

}
