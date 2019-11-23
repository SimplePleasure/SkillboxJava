import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyChangeFrame extends JFrame {
FullForm fullForm=new FullForm();
SimpleForm simpleForm=new SimpleForm();
    {
        setContentPane(fullForm);
        setMinimumSize(new Dimension(400,150));

        fullForm.addApplyPerson(e -> fullSwitch());
        simpleForm.addApplyPerson(e -> simpleSwitch());

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
                (KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK,false),"switchForms");

        getRootPane().getActionMap().put("switchForms", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchForm();
            }
        });

    }
    protected void switchForm(){
        if (getContentPane()==simpleForm)
            simpleSwitch();
        else if (getContentPane()==fullForm)
            fullSwitch();
        else new IllegalStateException();
    }
    protected void fullSwitch(){

        Person person=fullForm.getPerson();
        simpleForm.setPerson(person);
        setContentPanel(simpleForm);

    }
    protected void simpleSwitch(){

        Person  person=simpleForm.getPerson();
        fullForm.setPerson(person);
        setContentPanel(fullForm);
    }
    protected void setContentPanel(Container container){

        setContentPane(container);
        getContentPane().revalidate();
        getContentPane().repaint();

    }


}
