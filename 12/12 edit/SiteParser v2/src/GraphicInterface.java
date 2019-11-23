import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicInterface {
    private JPanel rootPanel;
    private JTextField urlField;
    private JButton start;
    private JButton stop;
    private JLabel time;
    private JLabel urlsCountLabel;
    private JTextArea viewResult;
    private JRadioButton saveOptions;
    private JScrollPane scrollPane;
    private JTextField pathToSave;

    DataPrepare dataPrepare;


    public GraphicInterface() {
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPrepare = new DataPrepare();
                dataPrepare.launch(urlField.getText(), time);
                start.setEnabled(false);
                stop.setEnabled(true);

            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPrepare.stopParsing(viewResult, urlsCountLabel, pathToSave);
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });

        saveOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                urlField.setVisible(!urlField.isVisible());
                urlsCountLabel.setVisible(!urlsCountLabel.isVisible());
                time.setVisible(!time.isVisible());
                start.setVisible(!start.isVisible());
                stop.setVisible(!stop.isVisible());
                pathToSave.setVisible(!pathToSave.isVisible());
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


}
