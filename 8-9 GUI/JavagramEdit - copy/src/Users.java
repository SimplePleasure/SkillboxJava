import java.util.ArrayList;


public class Users
{
    private String phone;
    private String name;





    public String getName(){
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public void editName(String name) {
        this.name = name;
    }






    Users(){}

    Users(String phone, String name) {
        this.phone = phone.replaceAll("[^0-9,\\+]", "");
        if (name.isEmpty()) {
            this.name = phone;
        }else {
            this.name = name;
        }
    }




    @Override
    public String toString(){
        return name;
    }

}
