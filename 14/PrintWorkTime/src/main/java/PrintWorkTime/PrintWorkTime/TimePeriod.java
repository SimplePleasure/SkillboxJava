package PrintWorkTime.PrintWorkTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod>{

    static SimpleDateFormat day = new SimpleDateFormat("dd");
    static SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
    String visitDate;
    Date from;
    Date to;

    TimePeriod (String visit) {
        try {
            visitDate = visit.substring(0, 10);
            Date visitTime = df.parse(visit.substring(11, 19));
            from = visitTime;
            to = visitTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public String getVisitDate() {
        return visitDate;
    }
    public void addTime(String time) {

        try {
            Date visit = df.parse(time);
            if (from.after(visit)) {
                from = visit;
            }
            if (to.before(visit)) {
                to = visit;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return df.format(from) + " - " + df.format(to);
    }

    @Override
    public int compareTo(TimePeriod o) {
        int currentDay = Integer.parseInt(visitDate.substring(8,10));
        int comparedDay = Integer.parseInt(o.getVisitDate().substring(8,10));
        if (currentDay>comparedDay) return 1;
        if (currentDay<comparedDay) return -1;
        return 0;
    }
}
