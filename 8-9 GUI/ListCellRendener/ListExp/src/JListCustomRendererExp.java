import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JListCustomRendererExp extends JFrame {

    private JList<Country> list1;
    private JPanel RootPanel;


    public JListCustomRendererExp() {

        setContentPane(getRootPanel());
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //========================================================================


//        ArrayList<Country> arrayList = new ArrayList<>();
//        arrayList.add(new Country("Belgia", "bg"));
//        arrayList.add(new Country("Russian Federation", "rf"));


        Country Rf = new Country("Russian Federation", "rf");
        Country Us = new Country("United States of America", "us");
        Country Jp = new Country("Japan", "jp");
        Country Bg = new Country("Belgia", "bg");


        DefaultListModel<Country> model = new DefaultListModel<>();
//        for (int i=0; i<arrayList.size(); i++){
//            model.addElement(arrayList.get(i));
//        }


        model.addElement(Rf);
        model.addElement(Us);
        model.addElement(Jp);
        model.addElement(Bg);


        list1.setModel(model);
        list1.setCellRenderer(new CountryRendener());


//        HashMap<String, String> spisok = new HashMap<>();
//        spisok.put( "rf", "Russian Federation");
//        spisok.put("us", "Unated States of America");
//        spisok.put("jp", "Japan");
//
//        DefaultListModel<String> listModel = new DefaultListModel<>();
//        for(String country: spisok.keySet()){
//            listModel.addElement(spisok.get(country));
//        }
//        list1.setModel(listModel);

    }


    public static void main(String[] args) {
        JFrame frame = new JListCustomRendererExp();
        frame.setVisible(true);

    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
