import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ScreenChanger extends JFrame {


    TelegramApiBridge td;
    static String productionHostAddress = "149.154.167.50:443";
    static int appId = 461984;
    static String appHash = "746eb50f5f37eb3d867dd38ea0f15a61";
    static String phoneNum = "+79043360860";
    static BufferedReader reader;

    public void sentCode(String phoneNum) {
        try {
            td = new TelegramApiBridge(productionHostAddress, appId, appHash);
            td.authSendCode(phoneNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void check(String code) {
        try {
            AuthAuthorization authorization = td.authSignIn(code);
            personal.setUserPhone(authorization.getUser().getPhone());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    PersonalData personal = new PersonalData();


    {

        CheckIn checkin = new CheckIn();
        //setUndecorated(true);
        setMinimumSize(new Dimension(800, 600));
        //setMaximumSize(new Dimension(1000, 800));
        setContentPane(checkin.getRootPanel());
        checkin.setButton(e -> confirm());

        checkin.setButton(e -> sentCode(phoneNum));
        checkin.setButton(e -> personal.setUserPhone(checkin.getNumber()));
        checkin.setCloseButton(e -> close());
    }

    public void CheckIn() {
        CheckIn checkin = new CheckIn();
        setMinimumSize(new Dimension(800, 600));
        setContentPane(checkin.getRootPanel());
        getContentPane().revalidate();
        checkin.setButton(e -> confirm());


        checkin.setButton(e -> sentCode(phoneNum));
        checkin.setButton(e -> personal.setUserPhone(checkin.getNumber()));

        checkin.setCloseButton(e -> close());
    }


    public void confirm() {

        Confirmation confirm = new Confirmation();

        //confirm.setPhoneNumber(phoneNumber);
        confirm.setPhoneNumber(personal.getUserPhone());




        setContentPane(confirm.getRootPanel());
        getContentPane().revalidate();

        confirm.setButton(e -> check(confirm.getPField()));
        confirm.setButton(e -> classGetName());
        confirm.setCloseButton(e -> close());

    }

    public void classGetName() {

        GetName getname = new GetName();
        setContentPane(getname.getRootPanel());
        getContentPane().revalidate();

        getname.setButton(e -> mainFrame());


        getname.setButton(e -> new Users(personal.getUserPhone(), personal.getUserName()));
        getname.setButton(e -> personal.setUserName(getname.getName()));

        getname.setCloseButton(e -> close());
    }

    public void mainFrame() {

        MainFrame main = new MainFrame();
        setContentPane(main.getRootPanel());
        getContentPane().revalidate();


        main.settingsButton(e -> settings());
        main.setUserName(personal.getUserName());

        main.addContact(e -> AddContact());
        main.setCloseButton(e -> close());
        main.setEditContactButton(e -> EditContact(main.getSelectedContact()));


        main.setContactList(personal.getContactList());


    }

    public void settings() {

        Settings set = new Settings();
        setContentPane(set.getRootPanel());
        getContentPane().revalidate();

        //set.phoneNumber(phoneNumber);
        set.setPhoneNumber(personal.getUserPhone());
        //set.setname(name);
        set.setname(personal.getUserName());

        set.setBackButton(e -> mainFrame());
        set.setExitButton(e -> CheckIn());

        set.setSaveButton(e -> mainFrame());
        set.setSaveButton(e -> personal.setUserName(set.getEditName()));

        set.setCloseButton(e -> close());
    }

    public void AddContact() {

        AddContact add = new AddContact();
        setContentPane(add.getRootPanel());
        getContentPane().revalidate();

        add.setBackButton(e -> mainFrame());
        add.setAddButton(e -> mainFrame());


        //add.setAddButton((e) -> personal.addContact(add.getPhone(), add.getName()));
        add.setAddButton(e -> personal.addPerson(new Users(add.getPhone(), add.getName())));


    }

    public void EditContact(String name) {
        EditContact edit = new EditContact();
        setContentPane(edit.getRootPanel());
        getContentPane().revalidate();


        String phoneNumber = personal.getNumber(name);
        edit.setContactName(name, phoneNumber);


        edit.setBackButton(e -> mainFrame());

        edit.setSaveButton(e -> mainFrame());
        edit.setSaveButton(e -> personal.editPerson(phoneNumber, edit.getEditContactName()));

        edit.setDelButton(e -> mainFrame());
//        edit.setDelButton( e -> personal.deleteContact(phoneNumber));


        edit.setDelButton(e -> personal.delContact(name));


    }


    public void close() {
        System.exit(0);
    }
}

