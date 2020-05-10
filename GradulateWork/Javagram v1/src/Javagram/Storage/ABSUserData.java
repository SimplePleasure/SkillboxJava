package Javagram.Storage;

import Javagram.interfaces.MainInterface;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;

public abstract class ABSUserData {

    static MainInterface.IStorage storage;
    static AuthAuthorization authorization;
    static String firstName;
    static String lastName;
    static String phone;
    static int userId;


    public static MainInterface.IStorage getStorage() {
        if (storage == null) {
            storage = new UserData();
        }
        return storage;
    }

}
