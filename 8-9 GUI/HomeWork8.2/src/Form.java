import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Form {
    private JPanel RootPanel;
    private JTextField surname;
    private JTextField name;
    private JTextField fatherName;
    private JButton button;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JProgressBar progressBar;
    private JLabel surnameLabel;
    private JLabel nameLabel;


    private void checkProgress(){
        if (!surname.getText().isEmpty() && !name.getText().isEmpty()&& !fatherName.getText().isEmpty()) {
            progressBar.setValue(3);} else if (
                !surname.getText().isEmpty() && !name.getText().isEmpty() && fatherName.getText().isEmpty() ||
                        !surname.getText().isEmpty() && name.getText().isEmpty()&& !fatherName.getText().isEmpty() ||
                        surname.getText().isEmpty() && !name.getText().isEmpty()&& !fatherName.getText().isEmpty()) {
            progressBar.setValue(2); } else if (!surname.getText().isEmpty() && name.getText().isEmpty()&& fatherName.getText().isEmpty() ||
                surname.getText().isEmpty() && !name.getText().isEmpty()&& fatherName.getText().isEmpty() ||
                surname.getText().isEmpty() && name.getText().isEmpty()&& !fatherName.getText().isEmpty()) {
            progressBar.setValue(1);} else if (surname.getText().isEmpty() && name.getText().isEmpty()&& fatherName.getText().isEmpty()){
            progressBar.setValue(0);}
    }



    public Form() {

        RootPanel.setLayout(new BoxLayout(RootPanel, BoxLayout.X_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!surname.getText().isEmpty() && !name.getText().isEmpty() && !fatherName.getText().isEmpty()) {
                    String line = surname.getText() + " " + name.getText() + " " + fatherName.getText();
                    Loader.sform.setLine(line);

                } else if ( !surname.getText().isEmpty() && !name.getText().isEmpty() && fatherName.getText().isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(
                            RootPanel,
                            "Do you want to continue with out patronimic?",
                            "Continue?",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.OK_OPTION) {
                        String line = surname.getText() + " " + name.getText() + " ";
                        Loader.sform.setLine(line);

                    }else if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
                        fatherName.requestFocus();
                    }
                }else if (surname.getText().isEmpty() || name.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            RootPanel, "You must type name and surname", "Error", JOptionPane.WARNING_MESSAGE
                    );
                    if (surname.getText().isEmpty()) {
                        surname.requestFocus();
                    } else {
                        name.requestFocus();
                    }
                }
            }
        });

        fatherName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.isControlDown() && e.getKeyCode() ==  KeyEvent.VK_ENTER) {
                    if (!surname.getText().isEmpty() && !name.getText().isEmpty() && !fatherName.getText().isEmpty()) {
                        String line = surname.getText() + " " + name.getText() + " " + fatherName.getText();
                        Loader.sform.setLine(line);

                    } else if ( !surname.getText().isEmpty() && !name.getText().isEmpty() && fatherName.getText().isEmpty()) {
                        int confirm = JOptionPane.showConfirmDialog (
                                RootPanel,
                                "Do you want to continue with out patronimic?",
                                "Continue?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (confirm == JOptionPane.OK_OPTION) {
                            String line = surname.getText() + " " + name.getText() + " ";
                            Loader.sform.setLine(line);

                        }else if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
                            fatherName.requestFocus();
                        }
                    }else if (surname.getText().isEmpty() || name.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                RootPanel, "You must type name and surname", "Error", JOptionPane.WARNING_MESSAGE);
                        if (surname.getText().isEmpty()) {
                            surname.requestFocus();
                        } else  {
                            name.requestFocus();
                        }
                    }
                }
            }
        });
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.isControlDown() && e.getKeyCode() ==  KeyEvent.VK_ENTER) {
                    if (!surname.getText().isEmpty() && !name.getText().isEmpty() && !fatherName.getText().isEmpty()) {
                        String line = surname.getText() + " " + name.getText() + " " + fatherName.getText();
                        Loader.sform.setLine(line);

                    } else if ( !surname.getText().isEmpty() && !name.getText().isEmpty() && fatherName.getText().isEmpty()) {
                        int confirm = JOptionPane.showConfirmDialog(
                                RootPanel,
                                "Do you want to continue with out patronimic?",
                                "Continue?",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.OK_OPTION) {
                            String line = surname.getText() + " " + name.getText() + " ";
                            Loader.sform.setLine(line);

                        }else if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
                            fatherName.requestFocus();
                        }
                    }else if (surname.getText().isEmpty() || name.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                RootPanel, "You must type name and surname", "Error", JOptionPane.WARNING_MESSAGE
                        );
                        if (surname.getText().isEmpty()) {
                            surname.requestFocus();
                        } else  {
                            name.requestFocus();
                        }
                    }
                }
            }
        });
        surname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.isControlDown() && e.getKeyCode() ==  KeyEvent.VK_ENTER) {
                    if (!surname.getText().isEmpty() && !name.getText().isEmpty() && !fatherName.getText().isEmpty()) {
                        String line = surname.getText() + " " + name.getText() + " " + fatherName.getText();
                        Loader.sform.setLine(line);

                    } else if ( !surname.getText().isEmpty() && !name.getText().isEmpty() && fatherName.getText().isEmpty()) {
                        int confirm = JOptionPane.showConfirmDialog(
                                RootPanel,
                                "Do you want to continue with out patronimic?",
                                "Continue?",
                                JOptionPane.YES_NO_OPTION
                                );
                        if (confirm == JOptionPane.OK_OPTION) {
                            String line = surname.getText() + " " + name.getText() + " ";
                            Loader.sform.setLine(line);

                        }else if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
                            fatherName.requestFocus();
                        }
                    }else if (surname.getText().isEmpty() || name.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                RootPanel, "You must type name and surname", "Error", JOptionPane.WARNING_MESSAGE
                        );
                        if (surname.getText().isEmpty()) {
                            surname.requestFocus();
                        } else  {
                            name.requestFocus();
                        }
                    }
                }
            }
        });


        surname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkProgress();
            }
        });

        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkProgress();
            }
        });

        fatherName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkProgress();
            }
        });

    }




    public JPanel getRootPanel() {
        return RootPanel;
    }

    public void setForm(String surname, String name, String fathername) {
        this.surname.setText(surname);
        this.name.setText(name);
        fatherName.setText(fathername);
        Loader.fioFrame.setVisible(false);
        Loader.frame.setVisible(true);
    }
}
