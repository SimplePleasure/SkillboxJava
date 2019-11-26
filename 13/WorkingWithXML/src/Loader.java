import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class Loader {
    static HashMap<Voter, Integer>  map = new HashMap<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {




       DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
       DocumentBuilder db = dbf.newDocumentBuilder();
       Document doc = db.parse("res/data-0.2M.xml");



       NodeList voter = doc.getElementsByTagName("voter");

       for (int i=0; i<voter.getLength(); i++) {
           Node voterNode = voter.item(i);
           NamedNodeMap nodeVoterMap = voterNode.getAttributes();

           String name = nodeVoterMap.getNamedItem("name").getNodeValue();
           String birthday = nodeVoterMap.getNamedItem("birthDay").getNodeValue();

           Voter v = new Voter(name, birthday);
           Integer count = map.get(v);


           map.put(v, count==null ? 1 : count+1);
       }


       for(Voter v:map.keySet()) {
           if (map.get(v)>1) {
               System.out.format("%-25s %10s %d\n",v.name, v.birthday, map.get(v));
           }
       }




//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        Document doc = db.parse("res/data-0.2M.xml");
//
//
//        NodeList votersList = doc.getDocumentElement().getElementsByTagName("voter");
//        NodeList visitList = doc.getDocumentElement().getElementsByTagName("visit");
//
//
//        for(int i=0; i<votersList.getLength(); i++) {
//
//
//
//            Node voter = votersList.item(i);
//            Node visit = visitList.item(i);
//            System.out.format("%-25s \t %s\n",voter.getAttributes().getNamedItem("name").getNodeValue(),
//                    visit.getAttributes().getNamedItem("time").getNodeValue());
//
//        }
    }
}
