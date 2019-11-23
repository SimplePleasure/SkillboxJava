import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShortForm {
    private JTextField fioField;
    private JPanel RootPanel;
    private JButton buttonOk;
    private JProgressBar progressBar1;

    public ShortForm() {
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String fioFragments[] = fioField.getText().trim().split("\\s");

                if( fioFragments.length == 3 ) {
                    Loader.form.setForm(fioFragments[0], fioFragments[1], fioFragments[2]);

                } else if (fioFragments.length == 2) {
                    int confirm = JOptionPane.showConfirmDialog(
                            RootPanel,
                            "Do you whant to continue with out last name?",
                            "Continue?",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.OK_OPTION) {
                        Loader.form.setForm(fioFragments[0], fioFragments[1], "");

                    } else if (confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION || confirm == JOptionPane.NO_OPTION) {
                        fioField.requestFocus();
                    }
                } else if (fioFragments.length < 2 ) {
                    JOptionPane.showMessageDialog(
                            RootPanel,
                            "You must enter your name und surname",
                            "Error", JOptionPane.WARNING_MESSAGE
                            );
                    fioField.requestFocus();
                }
            }
        });

        fioField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                progressBar1.setStringPainted(true);
                int progress = fioField.getText().split( "\\s+").length;
                if (!fioField.getText().isEmpty()) {
                    progressBar1.setValue(progress);
                } else { progressBar1.setValue(0); }

                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String fioFragments[] = fioField.getText().trim().split("\\s+");

                    if( fioFragments.length == 3 ) {
                        Loader.form.setForm(fioFragments[0], fioFragments[1], fioFragments[2]);

                    } else if (fioFragments.length == 2) {
                        int confirm = JOptionPane.showConfirmDialog(
                                RootPanel,
                                "Do you whant to continue with out last name?",
                                "Continue?",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.OK_OPTION) {
                            Loader.form.setForm(fioFragments[0], fioFragments[1], "");

                        }
                    } else if (fioFragments.length < 2 ) {
                        JOptionPane.showMessageDialog(
                                RootPanel,
                                "You must enter your name and surname",
                                "Error", JOptionPane.WARNING_MESSAGE );
                    }
                }


            }
        });
    }


    public void setLine(String fio) {
        fioField.setText(fio);
        Loader.frame.setVisible(false);
        Loader.fioFrame.setVisible(true);
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
