import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Danya on 24.02.2016.
 */
public class Loader {

    public static void main(String[] args) throws Exception {


        SwingUtilities.invokeLater(() -> {

            Output out = new Output();
            JFrame frame = new JFrame();
            frame.setContentPane(out.getRootPanel());
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

            try {
                out.statrReading();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


    private static void parseFile(String fileName) throws Exception {
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