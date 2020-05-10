package Javagram.Storage;

import Javagram.interfaces.MainInterface;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;

public class UserData extends ABSUserData implements MainInterface.IStorage {


    AuthAuthorization authorization;
    String firstName;
    String lastName;
    String phone;
    int userId;
//    Image smallPhoto;
//    Image photo;

    protected UserData() {}


    @Override
    public void logOut() {
        storage = null;
    }

    public void authorizationComplete(AuthAuthorization authAuthorization) {

        authorization = authAuthorization;
        firstName = authAuthorization.getUser().getFirstName();
        lastName = authAuthorization.getUser().getLastName();
        userId = authAuthorization.getUser().getId();

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