import javax.swing.*;
import java.awt.event.*;


public class Form {
    private JPanel rootPanel;
    protected JTextField textField;
    private JButton button1;
    private JTextField focusField;
    private JRadioButton yes;
    private JRadioButton no;
    private JProgressBar progressBar1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton button2;


    public Form(){

//        Loader.Operation op;
//        op = (x, y, z) -> x*y*z;
//        System.out.println(op.calc(3,4,5));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (yes.isSelected()) {
                    int variant = JOptionPane.showConfirmDialog(
                            rootPanel,
                            "ВВеди 10 символов",
                            "type 10 symbols",
                            JOptionPane.YES_NO_OPTION

                    );

                    if (variant == 0) {
                        passwordField1.requestFocus();
                        focusField.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                                super.keyReleased(e);
                                int length = focusField.getText().length();
                                progressBar1.setValue(length);
                                textField.setText( Integer.toString(length));
                            }
                        });
                    }
                }else if( no.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
            }
        });


        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                passwordField1.setEchoChar('$');
                textField1.setText(passwordField1.getText());
                int passLength = passwordField1.getText().length();
                progressBar1.setValue(passLength);
                passwordField1.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if (e.isShiftDown()){
                            textField1.selectAll();
                            textField1.cut();
                            focusField.grabFocus();
                            focusField.paste();

                        }else if (e.isControlDown()) {
                            passwordField1.selectAll();
                            passwordField1.setEchoChar((char)0);
                        }
                    }
                });


            }
        });


        final int[] startX = new int[1];
        final int[] startY = new int[1];
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                super.mouseClicked(ae);
                startX[0] = ae.getX();
                startY[0] = ae.getY();
            }
        });
        button2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int ex = e.getX() - startX[0];
                int ey = e.getY() - startY[0];
                Loader.frame.setLocation(Loader.frame.getX() + ex, Loader.frame.getY()+ey);

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}

