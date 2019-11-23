import javax.swing.*;
import java.awt.*;

public class MyChangeFrame extends JFrame {
FullForm fullForm=new FullForm();
SimpleForm simpleForm=new SimpleForm();
    {
        setContentPane(fullForm);
        setMinimumSize(new Dimension(600,400));

        fullForm.addApplyPerson (e -> fullSwitch());
        simpleForm.addApplyPerson(e -> simpleSwitch());
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

    }


}
