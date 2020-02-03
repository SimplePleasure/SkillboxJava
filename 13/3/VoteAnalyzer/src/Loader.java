import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Danya on 24.02.2016.
 */
public class Loader
{

    public static void main(String[] args) throws Exception
    {
        String fileName = "res/data-0.2M.xml";
        parseFile(fileName);

        ArrayList l = new ArrayList();
        l.add(1);
        l.add(2);
        l.add(0);
        l.add(9);
        l.add(5);


        l.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }

    private static void parseFile(String fileName) throws Exception
    {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Handler h = new Handler();
        sp.parse(fileName, h);
        h.printResults();
    }

}