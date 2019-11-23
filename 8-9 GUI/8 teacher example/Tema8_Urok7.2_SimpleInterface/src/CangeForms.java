import javax.swing.*;
import java.awt.*;

public class CangeForms extends JFrame {

    {
        FormPhone formPhone = new FormPhone();
        setContentPane(formPhone);
        formPhone.addApplyAction(e -> changeForm1());

    }
    protected void changeForm1(){
        FormCode  formCode=new FormCode();
        setContentPanel(formCode);
        formCode.addApplyAction(e -> changeForm2());
    }

    protected void changeForm2() {
        FormList formList=new FormList();
        setContentPanel(formList);
    }


    protected void setContentPanel(Container container){
    setContentPane(container);
    getContentPane().revalidate();
}

}