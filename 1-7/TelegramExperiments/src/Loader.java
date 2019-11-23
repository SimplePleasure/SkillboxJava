import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.UserContact;
import org.telegram.api.requests.TLRequestAuthLogOut;
import org.telegram.api.requests.TLRequestAuthSignIn;
import org.telegram.api.requests.TLRequestContactsGetContacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 461984, "746eb50f5f37eb3d867dd38ea0f15a61");

//        System.out.println("Please, type phone number");
//        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(reader.readLine().trim());
//        System.out.println(checkedPhone.isRegistered());
//        System.out.println(checkedPhone.isInvited());

        System.out.println("Please, type phone number to get the code");
        AuthSentCode sentcode = bridge.authSendCode(reader.readLine().trim().replaceAll("[^+0-9]",""));
        //bridge.authSendCode("+79043360860");

        System.out.println("Enter the confirm code");
        AuthAuthorization a = bridge.authSignIn(reader.readLine().trim());
        System.out.println(a.getUser());





        ArrayList<UserContact> myContactList = bridge.contactsGetContacts();
        for(int i=0; i<myContactList.size(); i++)
        {
            System.out.println(myContactList.get(i));
        }












    }
}
