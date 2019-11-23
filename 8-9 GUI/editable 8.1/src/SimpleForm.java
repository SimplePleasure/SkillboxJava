import javax.swing.*;
import java.awt.event.*;

public class SimpleForm //extends JPanel
{

    private JTextField fioField;
    private JLabel fio;
    private JButton ok;
    private JPanel RootPanel;
    private JProgressBar progressBar;

    public SimpleForm() {
        fioField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String data[] = fioField.getText().trim().split("\\s+");
                int progress = 0;
                if (!fioField.getText().isEmpty()){progress = data.length;}
                progressBar.setValue(progress);
            }
        });
    }

    public void setButton(ActionListener listener) {
        ok.addActionListener(listener);
    }

    public void setLine(String surname, String name, String fathername) {
        fioField.setText( surname + " " + name + " " + fathername);
    }
    public String[] getLine() {
        String person[] = fioField.getText().trim().split("\\s+");
        return person;
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
