import javax.swing.DefaultListModel;

public class ListRun {

    public static void main(String[] args) {
        String ini[] = {"������", "����", "���", "������"};
        DefaultListModel listModel = new DefaultListModel();
        for(int i=0;i<ini.length;i++) {
            listModel.addElement(ini[i]);
        }
        new Lists(listModel);
    }

}
 