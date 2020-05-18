package Javagram.TgAPI;

import Javagram.AppSettings.Config;
import Javagram.Storage.ABSUserData;
import Javagram.interfaces.MainInterface;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TelegramAPI implements MainInterface.IBridge {

    static TelegramApiBridge bridge;

    public static TelegramApiBridge getBridge() {
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

        if (authorization != null) {
            ABSUserData.getStorage().authorizationComplete(authorization);
            return true;
        }
        return false;
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

    public static void getContacts() throws IOException {
        List<UserContact> contacts = getBridge().contactsGetContacts();
        ABSUserData.getStorage().setContacts(contacts);
    }

    // TODO: 15.05.2020 sent dialogList & dialogLastMessge to UserData (invoked with successful sending confirmCode)
    public static void getDialogList() throws IOException{
        ArrayList<Dialog> dialogs = getBridge().messagesGetDialogs(0, 0, 100);
        ABSUserData.getStorage().setDialogList(dialogs);


        ArrayList<Message> lastMessges = getBridge().messagesGetMessages(dialogs.stream().
                map(Dialog::getTopMessage).collect(Collectors.toCollection(ArrayList::new)));
        ABSUserData.getStorage().setLastMessageList(lastMessges);

    }

    public static UserFull findUserById(int userId) throws IOException{
        return getBridge().usersGetFullUser(userId);
    }

}
