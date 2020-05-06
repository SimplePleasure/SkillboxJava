import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainScreen {


    private JPanel rootPanel;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JTextField textField;
    private JButton button;
    private JButton settingsButton;

    static Color background = new Color(87, 122, 167, 82);
    static Color selectionBackground = new Color(19, 83, 179, 89);
    static Color selectedForeground = new Color(24, 35, 45, 223);

    MainScreen() {


        listModel = new DefaultListModel<>();
        list.setModel(listModel);
//        list.setSelectionBackground(new Color(46, 116, 220, 45));
//        list.setSelectionForeground(new Color(5, 53, 90, 223));

        for (int i = 0; i < 10; i++) {
            listModel.addElement("test " + i);
        }


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listModel.addElement(textField.getText());
            }
        });
    }

    public void delSelected() {
        List<String> toDel = list.getSelectedValuesList();
        for (String del : toDel) {
            listModel.removeElement(del);
        }
    }

    public void setBackground(boolean b) {
        if (b) {
            button.setEnabled(false);
            settingsButton.setVisible(false);
            rootPanel.setBackground(background);

            list.setEnabled(false);
            list.setBackground(background);
            list.setForeground(selectedForeground);
            list.setSelectionBackground(selectionBackground);
            list.setSelectionForeground(selectedForeground);

            textField.setBackground(background);
            textField.setEnabled(false);

        } else {
            button.setEnabled(true);
            settingsButton.setVisible(true);
            rootPanel.setBackground(null);
            list.setEnabled(true);
            list.setBackground(null);
            list.setForeground(Color.black);
            list.setSelectionBackground(Color.blue);
            list.setSelectionForeground(Color.white);
            textField.setBackground(null);
            textField.setEnabled(true);
        }
    }

    public void delAllItemsFromList() {
        listModel = new DefaultListModel<>();
        list.setModel(listModel);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setSettingsButton(ActionListener listener) {
        settingsButton.addActionListener(listener);
    }
}
