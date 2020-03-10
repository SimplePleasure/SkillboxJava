package PrintWorkTime.PrintWorkTime;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.TreeMap;

public class Handler extends DefaultHandler {


    static TreeMap<Integer, VoteStationWorkTime> workTimeStorage = new TreeMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("visit")) {
            Integer station = Integer.parseInt(attributes.getValue("station"));
            String visit = attributes.getValue("time");


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



    @Override
    public void endDocument() throws SAXException {




    }
}
