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
public class Loader {

    public static void main(String[] args) throws Exception
    {
        String fileName = "res/data-0.2M.xml";
        long start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println("Total time: " + (System.currentTimeMillis()-start));
    }

    private static void parseFile(String fileName) throws Exception
    {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        Handler h = new Handler();
        parser.parse(fileName, h);
    }

}