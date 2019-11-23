
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Loader {
    public static void main (String[] args)
    {

//      Подсказка преподавателя после сдачи работы:
//        Calendar newDate = Calendar.getInstance();
//        newDate.set(1990, newDate.AUGUST, 14);
//        newDate.add(Calendar.YEAR, 3);
//        System.out.println(newDate.getWeekYear());


        Calendar birdhday = Calendar.getInstance();
        birdhday.set(1993  , birdhday.AUGUST,14);
        Date dateBirthday = birdhday.getTime();

        Date date = new Date();
        long age = (date.getTime()/1000/60/60/24/365) - (dateBirthday.getTime()/1000/60/60/24/365);

        Calendar myBirthdayDate = Calendar.getInstance();
        myBirthdayDate.set(1993, myBirthdayDate.AUGUST, 14);

        for (int i=0; i<=25; i++) {
            Date myBirthday = myBirthdayDate.getTime();
            DateFormat newFormat = new SimpleDateFormat("\tEE\tdd.MM.yyyy", Locale.ENGLISH);

            long age2 = date.getTime() / 1000 / 60 / 60 / 24 / 365 - myBirthday.getTime() / 1000 / 60 / 60 / 24 / 365;
            long printAge = age - age2;
            System.out.println("Age: " + printAge + "\t " + newFormat.format(myBirthdayDate.getTime()));
            myBirthdayDate.add(Calendar.YEAR, 1);
        }



    }
}