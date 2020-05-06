import javax.swing.*;
import java.awt.*;

public class ScreenChanger extends JFrame {

    MainScreen main;
    SettingsScreen settings;

    JLayeredPane layers;
    JPanel contentPane;


    ScreenChanger() {

        contentPane = new JPanel();
        layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(600, 400));

        main = new MainScreen();
        main.getRootPanel().setBounds(0,0, 600, 400);
        main.setSettingsButton(e -> changeLayers());

        settings = new SettingsScreen();
        settings.setChangeButton(e -> changeLayers());
        settings.getRootPanel().setBounds(0,0,600,366);
        settings.getRootPanel().setBackground(new Color(83, 158, 113, 81));

        layers.add(settings.getRootPanel(), new Integer(2),1);
        layers.add(main.getRootPanel(), new Integer(3), 0);

        contentPane.add(layers);
        setSize(new Dimension(610, 435));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(contentPane);
        setVisible(true);

    }

    public void changeLayers() {
        int mainLayer = JLayeredPane.getLayer(main.getRootPanel());
        if (mainLayer == 3) {
            layers.setLayer(main.getRootPanel(), 1, 0);
            main.setBackground(true);
        } else {
            if (settings.clearList()) {
                main.delAllItemsFromList();
            }
            if (settings.delSelected()) {
                main.delSelected();
            }
            settings.clearSelections();
            main.setBackground(false);
            layers.setLayer(main.getRootPanel(), 3, 0);
        }

    }
}
