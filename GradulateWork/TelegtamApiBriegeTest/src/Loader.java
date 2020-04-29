import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.UserContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {

    static String productionHostAddress = "149.154.167.50:443";
    static int appId = 461984;
    static String appHash = "746eb50f5f37eb3d867dd38ea0f15a61";
    static BufferedReader reader;

    public static void main(String[] args) {
        reader = new BufferedReader(new InputStreamReader(System.in));


        try {

            TelegramApiBridge telegram = new TelegramApiBridge(productionHostAddress, appId, appHash);
            System.out.println("Please, type phone:");
            String phone = reader.readLine().trim();
            AuthCheckedPhone checkedPhone = telegram.authCheckPhone(phone);

            AuthAuthorization authorization;
            if (checkedPhone.isRegistered()) {
                AuthSentCode sentCode = telegram.authSendCode(phone);
                System.out.println(sentCode.getPhoneCodeHash());
                System.out.println("enter the code");
                authorization = telegram.authSignIn(reader.readLine());
                System.out.println("User: " + "\n" +
                        authorization.getUser().getLastName() + "\t" + authorization.getUser().getFirstName() + "\n" +
                        authorization.getUser().getPhone());

                ArrayList<UserContact> userList = telegram.contactsGetContacts();
//                for (UserContact contact : userList) {
//                    System.out.println("___________________");
//                    System.out.println("Name: " + contact.getFirstName() +" "+ contact.getLastName() +
//                            "\nPhone:" + contact.getPhone() + "\nID: " + contact.getId() +
//                            "\nIs online: " + contact.isOnline());
//                    System.out.println("___________________");
//                }


                /*
                Name: Андрей Металистов 78
                Phone:79110888802
                ID: 696435093
                Is online: false
                 */

                telegram.messagesSendMessage(696435093, "test message", 87657658);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
