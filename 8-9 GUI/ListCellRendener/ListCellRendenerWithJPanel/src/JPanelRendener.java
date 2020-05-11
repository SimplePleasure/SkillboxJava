import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JPanelRendener implements ListCellRenderer<JPanel> {


    @Override
    public Component getListCellRendererComponent(JList<? extends JPanel> jList,
                                                  JPanel jPanel, int i, boolean b, boolean b1) {



        jPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("test");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
            }
        });

        return jPanel;
    }


}
