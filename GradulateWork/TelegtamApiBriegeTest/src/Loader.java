import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.Dialog;
import org.javagram.response.object.Message;
import org.javagram.response.object.UserContact;
import org.telegram.api.TLAbsInputUser;
import org.telegram.api.TLDecryptedMessage;
import org.telegram.api.TLInputPeerChat;
import org.telegram.api.TLInputPeerContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {

    static String productionHostAddress = "149.154.167.50:443";
    static int appId = 461984;
    static String appHash = "746eb50f5f37eb3d867dd38ea0f15a61";
    static String phoneNum = "+79043360860";
    static BufferedReader reader;

    public static void main(String[] args) {
        reader = new BufferedReader(new InputStreamReader(System.in));


        try {

            TelegramApiBridge telegram = new TelegramApiBridge(productionHostAddress, appId, appHash);
            telegram.authSendCode(phoneNum);

            System.out.println("enter the code");
            AuthAuthorization authorization = telegram.authSignIn(reader.readLine());
            System.out.println("User: " + authorization.getUser().getLastName() + "\t" + authorization.getUser().getFirstName());



            ArrayList<Dialog> dialogList = telegram.messagesGetDialogs(0, 25000, 100);
            System.err.println("\n\n\n" + dialogList.size() + "\n\n\n");



            TLInputPeerContact c = new TLInputPeerContact(433997774);



            int amount;
            for (Dialog dialog : dialogList) {

                int topMessage = dialog.getTopMessage();

                ArrayList<Message> messages = telegram.messagesGetHistory(topMessage, 100, 20);
                for (Message m : messages) {
                    amount = m.getFromId()+m.getToId();
                    System.out.println(m.getFromId() + "\n" +m.getMessage() + "\n~~~~~~~~~~~~~~~~");
                }

            }












//            ArrayList<UserContact> userList = telegram.contactsGetContacts();
//            for (UserContact contact : userList) {
//                System.out.println(contact.getFirstName() + " " + contact.getLastName() + "\n" + contact.getId() + "\n_______________");
//            }

//            String str = reader.readLine();
//            telegram.messagesSendMessage(433997774, str, 48654876 );


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
