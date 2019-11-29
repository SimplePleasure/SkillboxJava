public class Voter {

    String name;
    String birthday;

    Voter (String name, String birthDay) {
        this.name = name;
        this.birthday = birthDay;
    }


    /*
    Переопределение методов equals и hashCode выполнено для работы HashMap в классе Loader
    При создании нового объекта Voter и добавлении его в
     */
    @Override
    public boolean equals(Object obj)
    {
        Voter voter = (Voter) obj;
        return name.equals(voter.name) && birthday.equals(voter.birthday);
    }
    @Override
    public int hashCode()
    {
        long code = name.hashCode() + birthday.hashCode();
        while(code > Integer.MAX_VALUE) {
            code = code/10;
        }
        return (int) code;
    }



}
