import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Handler extends DefaultHandler {

    private HashMap<String, WorkTime> voteStationWorkTimes = new HashMap<>();


    @Override   // Создание Connection и PreparedStatement
    public void startDocument() {
        DBConnection.getConnection();
    }

    @Override   // Дописываем остаток
    public void endDocument() {
        DBConnection.executeQuery();
        printWorkTime();
        DBConnection.printVoterCounts();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            try {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                DBConnection.countVoter(name, birthDay);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (qName.equals("visit")) {

            String station = attributes.getValue("station");
            String visit = attributes.getValue("time");
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(visit);
        }
    }

    void printWorkTime() {
        for (Map.Entry<String, WorkTime> workTime : voteStationWorkTimes.entrySet()) {
            System.out.println("Уч." + workTime.getKey() + "\t" + workTime.getValue());
        }
    }
}
