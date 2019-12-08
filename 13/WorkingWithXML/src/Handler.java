import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Handler extends DefaultHandler {

    HashMap<Voter, Integer> votersCounter = new HashMap<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            Voter voter = new Voter(name, birthDay);
            votersCounter.put(voter, votersCounter.get(voter)== null ? 1 : votersCounter.get(voter)+1);
        }
        if (qName.equals("visit")) {
            try {
                Integer station = Integer.parseInt(attributes.getValue("station"));
                Date visit = dateFormat.parse(attributes.getValue("time"));
                System.out.println("Station " + station + "\t" + visit);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public void printResult() {
        for (Voter v : votersCounter.keySet()) {
            if (votersCounter.get(v)>1)
            System.out.format("%-25s %15s %d\n",v.name, v.birthday, votersCounter.get(v));
        }
    }
}
