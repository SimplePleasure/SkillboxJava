package Javagram.interfaces;

import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.Dialog;
import org.javagram.response.object.Message;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface MainInterface {



    interface IViev {
        JPanel getRootPanel();
    }

    interface IEnterPhoneNum extends IViev {
        void checkPhone(String phone);

    }

    interface IConfirmPhone extends IViev {

        void sentConfirmCode(String code);
    }

    interface IRegister extends IViev {
        void finishRegister(String firstName, String lastName);
    }

    interface IMainScreen extends IViev{

    }

    interface IDialogs extends IViev {



        void setPhoto(byte[] icon);
        int getUserId();
        boolean isOnline();
        Message getLastMessage();
        void removeUISelection();
        void setUISelection();


    }



    interface IBridge {
//        boolean logOut();
    }

    interface IStorage {

        void setLastMessageList(List<Message> messages);
        void setContacts(List<UserContact> userList);
        void setDialogList(List<Dialog> list);
        void authorizationComplete(AuthAuthorization authAuthorization);
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setPhone(String phone);
        void setUserId(int userId);

        UserContact getUserById(int id);
        Map<Integer, UserContact> getContacts();
        List<Message> getLastMessageList();
        List<Dialog> getDialogList();
        AuthAuthorization getAuthorization();
        String getFirstName();
        String getLastName();
        String getPhone();
        int getUserId();
    }






}
