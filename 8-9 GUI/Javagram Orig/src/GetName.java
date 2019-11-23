import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GetName {
    private JPanel RootPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JTextPane введитеВашеИмяИTextPane;
    private JPanel dataPanel;
    private JPanel photoPanel;
    private JPanel namePanel;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton Continue;

    public GetName() {

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        rollupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.getNameframe.setState(JFrame.ICONIFIED);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.getNameframe.setVisible(false);
                System.exit(0);
            }
        });



        nameField.setText("Имя");
        surnameField.setText("Фамилия");


        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                nameField.setText("");
            }
        });
        surnameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                surnameField.setText("");
            }
        });



        surnameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                boolean complete = !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() &&
                        !nameField.getText().equals("Имя") && !surnameField.getText().equals("Фамилия");
                if (complete) { Continue.setEnabled(true);}
                if (!complete) {Continue.setEnabled(false);}
            }
        });
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                boolean complete = !nameField.getText().isEmpty() && !surnameField.getText().isEmpty();
                if (complete) { Continue.setEnabled(true);}
                if (!complete) {Continue.setEnabled(false);}
            }
        });

        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.getNameframe.setVisible(false);
                Loader.main.setVisible(true);
            }
        });
    }

    public JPanel getRootPanel() {
        return RootPanel;
    }
}
