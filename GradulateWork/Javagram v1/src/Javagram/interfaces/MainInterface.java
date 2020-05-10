package Javagram.interfaces;

import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;

import javax.swing.*;

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



    interface IBridge {
//        boolean logOut();
    }

    interface IStorage {

        void logOut();

        void authorizationComplete(AuthAuthorization authAuthorization);
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setPhone(String phone);
        void setUserId(int userId);


        AuthAuthorization getAuthorization();
        String getFirstName();
        String getLastName();
        String getPhone();
        int getUserId();
    }






}
