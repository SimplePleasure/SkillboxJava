import javax.swing.*;

public class Person
{

    private String surname;
    private String name;
    private String fathername;

    public int getCount() {
        int x=0;
        if (!surname.isEmpty()) {x +=1;}
        if (!name.isEmpty()) {x +=1;}
        if (!fathername.isEmpty()) {x+=1;}
        return x;
    }

    public Person(String surname, String name, String fathername){
        this.surname = surname;
        this.name = name;
        if (fathername.isEmpty()) {this.fathername = " ";}
        this.fathername = fathername;
    }


    public String[] getPerson(){
        String person[] = new String[3];
        person[0] = surname.trim();
        person[1] = name.trim();
        person[2] = fathername.trim();

        return person;
    }

}
