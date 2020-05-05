import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.Dialog;
import org.javagram.response.object.Message;
import org.javagram.response.object.UserContact;

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


//            ArrayList<Integer> dialog = new ArrayList<>();
//
//            ArrayList<Dialog> m = telegram.messagesGetDialogs(0, 25000, 100);
//            System.out.println("\n\n\n" + m.size() + "\n\n\n");
//            for (Dialog d : m) {
//                int top = d.getTopMessage();
//                for (int i=0; i<15; i++) {
//                    dialog.add(top-i);
//                }
//            }
//            ArrayList<Message> list = telegram.messagesGetMessages(dialog);
//            for (Message q : list) {
//                System.err.println(q.getMessage());
//            }
//
//
//
//            telegram.authLogOut();









//                ArrayList<UserContact> userList = telegram.contactsGetContacts();
//                for (UserContact contact : userList) {
//                    System.out.println(contact.getFirstName() + " " + contact.getLastName() + "\n" + contact.getId() + "\n_______________");
//                }

                /*
                _______________
                Папа
                433997774
                _______________
                 */

            String str = reader.readLine();
            telegram.messagesSendMessage(433997774, str, 48654876 );



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
