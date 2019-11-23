import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JPanel rootPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel contactPanel;
    private JPanel dialogWindow;
    private JList list1;

    MainFrame(){


        rollupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.main.setState(JFrame.ICONIFIED);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.main.setVisible(false);
                System.exit(0);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
