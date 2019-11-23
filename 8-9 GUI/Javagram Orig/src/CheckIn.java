import javax.swing.*;
import java.awt.event.*;

public class CheckIn {
    private JPanel rootPanel;
    protected JPanel topPanel;
    private JPanel bottomPanel;
    private JButton closeButton;
    private JButton rollupButton;
    private JTextPane text;
    private JTextField numberField;
    private JButton next;



    public CheckIn() {




        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        rollupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.registration.setState(JFrame.ICONIFIED);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.registration.setVisible(false);
                System.exit(0);
            }
        });

        numberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                numberField.setText("+7 ");


                numberField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        super.keyReleased(e);
                        String line;
                        if (numberField.getText().length() == 6) {
                            line = numberField.getText().substring(3);
                            numberField.setText("+7 " + "(" + line + ") ");


                        }
                        if (numberField.getText().length() == 12) {
                            numberField.setText(numberField.getText()+ " ");
                        }
                        if (numberField.getText().length() == 15) {
                            numberField.setText(numberField.getText() + " " );
                        }
                        boolean complete = numberField.getText().length()==18;
                        if (complete) {next.setEnabled(true);}
                        if (!complete) {next.setEnabled(false);}
                    }

                });



            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.registration.setVisible(false);
                Loader.confirm.setVisible(true);
                Loader.confirmation.getNumber(numberField.getText());
            }
        });

        final int[] ex = new int[1];
        final int[] ey = new int[1];

        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ex[0] = e.getX();
                ey[0] = e.getY();
            }
        });
        topPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent a) {
                super.mouseDragged(a);
                int ax = a.getX()-ex[0];
                int ay = a.getY()-ey[0];
                Loader.registration.setLocation(Loader.registration.getX()+ax, Loader.registration.getY()+ay);
            }
        });



    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
