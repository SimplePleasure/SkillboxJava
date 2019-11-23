import java.util.ArrayList;


public class PersonalData {





    public ArrayList<Users> personalDataArrayList = new ArrayList<>();
    ArrayList getContactList(){
        return personalDataArrayList;
    }
    void addPerson (Users user){
        personalDataArrayList.add(user);
    }
    void editPerson (String phone, String name) {
        for (Users user : personalDataArrayList) {
            if (user.getPhone().equals(phone)) {
                user.editName(name);
            }
        }
    }
    void delContact(String name) {
        try {
            for (Users user : personalDataArrayList) {
                if (user.getName().equals(name)) {
                    personalDataArrayList.remove(user);
                    user = null;
                    break;
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("Exception! Empty contact list.");
        }
    }
    String getNumber(String name) {
        String phone = "";
        for (Users user : personalDataArrayList) {
            if (user.getName().equals(name)) {
                phone =  user.getPhone();
            }
        }
        return phone;
    }
    Users getUser(String name) {
        for (Users user : personalDataArrayList) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
















    private String userPhone;
    private String userName;
    protected void setUserPhone(String phone){
        userPhone = phone;
    }
    protected String getUserPhone() {
        return userPhone;
    }
    protected void setUserName(String name) {
        userName = name;
    }
    protected  String getUserName() {
        return userName;
    }









}
