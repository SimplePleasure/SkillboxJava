import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Changer extends JFrame
{
    FullForm full = new FullForm();
    SimpleForm simple = new SimpleForm();
    {
        setContentPane(full.getRootPanel());
        setMinimumSize(new Dimension(300, 155));

        full.setButton( e-> toSimple());
        simple.setButton( e-> toFull());

    }

    {
        full.getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), "altent");
        full.getRootPanel().getActionMap().put("altent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(full.sout());
            }
        });

        full.getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(
                        KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "ctrlEnter");
        full.getRootPanel().getActionMap().put("ctrlEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toSimple();
            }
        });

        simple.getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "ctrlEnter");
        simple.getRootPanel().getActionMap().put("ctrlEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toFull();
            }
        });

    }





    public void toSimple(){
        Person person = new Person(full.getSurname(), full.getName(), full.getFatherName());
        int option = 1;
        if (person.getPerson()[0].isEmpty() || person.getPerson()[1].isEmpty() ||
        person.getPerson()[0].isEmpty() && person.getPerson()[1].isEmpty()) {
            String text = null;
            if (person.getPerson()[0].isEmpty() && person.getPerson()[1].isEmpty()) {text =" name and surname";}
            else if (person.getPerson()[0].isEmpty()){text = " surname";} else if
            (person.getPerson()[1].isEmpty()) {text = " name";}
            JOptionPane.showMessageDialog(full.getRootPanel(), "You must enter"+ text);
        }else if (person.getPerson()[2].isEmpty()) {option = JOptionPane.showConfirmDialog
                    (full.getRootPanel(),
                    "Do You whant to continue without patronimic?", "Continue?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); }
        if (option == JOptionPane.OK_OPTION || !person.getPerson()[2].isEmpty()) {
            simple.setLine(person.getPerson()[0] + " ", person.getPerson()[1] + " ", person.getPerson()[2]);
            setContentPane(simple.getRootPanel());
            getContentPane().revalidate();
        }
    }
    public void toFull(){

        Person person = new Person(simple.getLine()[0], simple.getLine()[1], simple.getLine()[2]);
        full.setData(person.getPerson()[0], person.getPerson()[1], person.getPerson()[2]);
        setContentPane(full.getRootPanel());
        getContentPane().revalidate();
    }


}
