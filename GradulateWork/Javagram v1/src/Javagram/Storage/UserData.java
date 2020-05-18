package Javagram.Storage;

import Javagram.interfaces.MainInterface;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.object.Dialog;
import org.javagram.response.object.Message;
import org.javagram.response.object.UserContact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData extends ABSUserData implements MainInterface.IStorage {

    AuthAuthorization authorization;
    String firstName;
    String lastName;
    String phone;
    int userId;
//    Image smallPhoto;
//    Image photo;
    Map<Integer, UserContact> userContact;
    List<Dialog> dialogList;
    List<Message> lastMessages;

    protected UserData() {}

    public void authorizationComplete(AuthAuthorization authAuthorization) {
        authorization = authAuthorization;
        firstName = authAuthorization.getUser().getFirstName();
        lastName = authAuthorization.getUser().getLastName();
        userId = authAuthorization.getUser().getId();
    }


    public void setLastMessageList(List<Message> messages) {
        lastMessages = messages;
    }

    public void setContacts(List<UserContact> userList) {
        userContact = new HashMap<>();
        for (UserContact c : userList) {
            userContact.put(c.getId(), c);
        }
    }

    public void setDialogList(List<Dialog> list) {
        dialogList = list;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }




    public UserContact getUserById(int id) {
        return userContact.get(id);
    }

    public List<Message> getLastMessageList() {
        return lastMessages;
    }

    public Map<Integer, UserContact> getContacts() {
        return userContact;
    }

    public List<Dialog> getDialogList() {
        return dialogList;
    }

    public AuthAuthorization getAuthorization() {
        return authorization;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public int getUserId() {
        return userId;
    }






    

}