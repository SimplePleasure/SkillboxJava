import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyChangeFrame extends JFrame {
    FullForm fullForm = new FullForm();
    SimpleForm simpleForm = new SimpleForm();

    {
        setContentPane(fullForm);
        setMinimumSize(new Dimension(400, 150));

        fullForm.addApplyPerson(e -> fullSwitch());
        simpleForm.addApplyPerson(e -> simpleSwitch());

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "switchForms");

        getRootPane().getActionMap().put("switchForms", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchForm();
            }
        });

    }

    protected void switchForm() {
        if (getContentPane() == simpleForm)
            simpleSwitch();
        else if (getContentPane() == fullForm)
            fullSwitch();
        else new IllegalStateException();
    }

    protected void fullSwitch() {

        Person person = fullForm.getPerson();
        if (canSwitch(checkPerson(person))) {
            simpleForm.setPerson(person);
            setContentPanel(simpleForm);
        }

    }

    protected void simpleSwitch() {

        Person person = simpleForm.getPerson();
        if (canSwitch(checkPerson(person))) {
            fullForm.setPerson(person);
            setContentPanel(fullForm);
        }
    }

    protected void setContentPanel(Container container) {

        setContentPane(container);
        getContentPane().revalidate();
        getContentPane().repaint();

    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static final int PERSON_OK = 0, SURNAME_OR_NAME_MISSING = 1, FATHERNAME_MISSING = 2;

    public static int checkPerson(Person person) {
        if (isEmpty(person.getSurname()) || isEmpty(person.getName()))
            return SURNAME_OR_NAME_MISSING;
        else if (isEmpty(person.getFathername()))
            return FATHERNAME_MISSING;
        else return PERSON_OK;

    }

    protected boolean canSwitch(int status) {
        if (status == PERSON_OK) return true;
        else if (status == SURNAME_OR_NAME_MISSING) {

            JOptionPane.showOptionDialog(this, "Необходимо заполнить имя или фамилию",
                    "Не все данные заполнены", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, null, null);
            return false;
        }

        else if (status==FATHERNAME_MISSING)
        {
           return JOptionPane.YES_OPTION==JOptionPane.showOptionDialog(this,"Уверены ли вы в том, что не хотите установить отчество?",
                   "Не все данные заполнены",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
             }
             else throw new IllegalArgumentException();

    }


}
