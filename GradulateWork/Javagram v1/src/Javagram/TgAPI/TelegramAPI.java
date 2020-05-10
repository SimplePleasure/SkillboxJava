package Javagram.TgAPI;

import Javagram.AppSettings.Config;
import Javagram.Storage.ABSUserData;
import Javagram.interfaces.MainInterface;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;

import java.io.IOException;
import java.util.List;

public class TelegramAPI implements MainInterface.IBridge {

    static TelegramApiBridge bridge;

    static TelegramApiBridge getBridge() {
        if (bridge == null) {
            try {
                bridge = new TelegramApiBridge(Config.productionHostAddress,
                        Integer.parseInt(Config.appId), Config.appHash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bridge;
    }

    public static boolean isPhoneRegistred (String phone) throws IOException {
        AuthSentCode authSentCode = getBridge().authSendCode(phone);
        if (authSentCode != null) {

            ABSUserData.getStorage().setPhone(phone);
            return authSentCode.isRegistered();
        }
        return false;
    }

    public static boolean sentConfirmCode (String code) throws IOException {
        AuthAuthorization authorization =  getBridge().authSignIn(code);
        ABSUserData.getStorage().authorizationComplete(authorization);
        return authorization!=null;
    }

    public static boolean fillInitials(String firstName, String lastName) throws IOException{

        if (!ABSUserData.getStorage().getFirstName().equals(firstName) ||
                 !ABSUserData.getStorage().getLastName().equals(lastName)) {
            User user = getBridge().accountUpdateProfile(firstName, lastName);
            if (user != null) {
                ABSUserData.getStorage().setFirstName(firstName);
                ABSUserData.getStorage().setLastName(lastName);
            } else {
                return false;
            }
        }
        return true;
    }

    public static List<UserContact> getContacts() throws IOException {
        return getBridge().contactsGetContacts();
    }
















//    @Override
//    public boolean logOut() {
//        try {
//            getBridge().authLogOut();
//            bridge = null;
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
