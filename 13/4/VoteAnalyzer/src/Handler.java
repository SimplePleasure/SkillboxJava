import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Handler extends DefaultHandler {

    private SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private HashMap<Voter, Integer> voterCounts = new HashMap<>();




    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            try {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                DBConnection.countVoter(name, birthDay);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (qName.equals("visit")) {
            try {
                int station = Integer.parseInt(attributes.getValue("station"));
                Date visit = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(visit.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void printResults() {
        System.out.println("Voting station work time: ");
        for (Integer i : voteStationWorkTimes.keySet()) {
            System.out.format("уч. %-7d %10s\n", i, voteStationWorkTimes.get(i));
        }
        try {
            DBConnection.printVoterCounts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
