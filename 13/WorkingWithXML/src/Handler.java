import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class Handler extends DefaultHandler {

    HashMap<Voter, Integer> votersCounter = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            Voter voter = new Voter(name, birthDay);
            votersCounter.put(voter, votersCounter.get(voter)== null ? 1 : votersCounter.get(voter)+1);
        }
    }
    public void printResult() {
        for (Voter v : votersCounter.keySet()) {
            if (votersCounter.get(v)>1)
            System.out.format("%-25s %15s %d\n",v.name, v.birthday, votersCounter.get(v));
        }
    }
}
