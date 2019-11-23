import javax.swing.*;
import java.awt.event.*;

public class Form extends JPanel {
    private JPanel rootPanel;
    private JLabel label1;
    private JLabel labelData;
    private JLabel labelShow;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    // ======  Но как обрабатывать клавиши tab, alt, ctrl, shift не знал. =========
    {

        getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, 0, false), "Enter");
        getRootPanel().getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelShow.setText(KeyEvent.getKeyText(KeyEvent.VK_ENTER));
            }
        });

        getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ESCAPE, 0, false), "Esc");
        getRootPanel().getActionMap().put("Esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelShow.setText(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE));
            }
        });

        getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_CAPS_LOCK, 0, false), "capsLock");
        getRootPanel().getActionMap().put("capsLock", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelShow.setText(KeyEvent.getKeyText(KeyEvent.VK_CAPS_LOCK));
            }
        });

        getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "CtrlEnter");
        getRootPanel().getActionMap().put("CtrlEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelShow.setText(KeyEvent.getKeyText(KeyEvent.VK_CONTROL) + " + " + KeyEvent.getKeyText(KeyEvent.VK_ENTER));
            }
        });

        getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), "AltEnter");
        getRootPanel().getActionMap().put("AltEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelShow.setText(KeyEvent.getKeyText(KeyEvent.VK_ALT) + " + " + KeyEvent.getKeyText(KeyEvent.VK_ENTER));
            }
        });
    }

}

