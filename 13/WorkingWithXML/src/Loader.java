import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Loader {
    static HashMap<Voter, Integer>  map = new HashMap<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File("res/data-18M.xml"), handler);

        handler.printResult();







//       DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
//       DocumentBuilder db = dbf.newDocumentBuilder();
//       Document doc = db.parse("res/data-0.2M.xml");
//       NodeList voters = doc.getElementsByTagName("voter");
//
//       for (int i=0; i<voters.getLength(); i++) {
//           Node voter = voters.item(i);
//
//           NamedNodeMap nodeVoterMap = voter.getAttributes();
//
//           String name = nodeVoterMap.getNamedItem("name").getNodeValue();
//           String birthday = nodeVoterMap.getNamedItem("birthDay").getNodeValue();
//
//           Voter v = new Voter(name, birthday);
//           Integer count = map.get(v);
//
//
//           map.put(v, count==null ? 1 : count+1);
//       }
//
//       for(Voter v:map.keySet()) {
//           if (map.get(v)>1) {
//               System.out.format("%-25s %10s %d\n",v.name, v.birthday, map.get(v));
//           }
//       }
//
//
//
    }
}
