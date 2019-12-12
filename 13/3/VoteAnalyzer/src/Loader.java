import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Danya on 24.02.2016.
 */
public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-0.2M.xml";
        parseFile(fileName);
    }

    private static void parseFile(String fileName) throws Exception {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        Handler h = new Handler();
        h.preparePartiallyReading();
        parser.parse(fileName, h);

//=====================PrintResults==============================================================
//        HashMap<Voter, Integer> voters = h.getVoters();
//        for (Voter voter: voters.keySet()) {
//            if (voters.get(voter) > 1) {
//                System.out.format("%s, %d \n", voter.getName(), voters.get(voter));
//            }
//        }
//================================================================================================
    }

}