import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Danya on 24.02.2016.
 */
public class TimePeriod implements Comparable<TimePeriod> {
//    private long from;
//    private long to;


    static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    String date;
    Date timeFrom;
    Date timeTo;


    /**
     * Time period within one day
     *
     * @param from
     * @param to
     */
    public TimePeriod(String date) {
        try {
            this.date = date.substring(0, "2010.10.10".length());
            Date time = timeFormat.parse(date.substring(11, 19));
            timeFrom = time;
            timeTo = time;

        } catch (ParseException e) {
            e.printStackTrace();
        }
//        this.f = from;
//        this.t = to;

//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//
//        if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
//            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

//    public TimePeriod(Date from, Date to)
//    {
//        this.from = from.getTime();
//        this.to = to.getTime();
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//        if(!dayFormat.format(from).equals(dayFormat.format(to)))
//            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
//    }

//    public void appendTime(Date visitTime)
//    {
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//        if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(visitTime.getTime()))))
//            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
//        long visitTimeTs = visitTime.getTime();
//        if(visitTimeTs < from) {
//            from = visitTimeTs;
//        }
//        if(visitTimeTs > to) {
//            to = visitTimeTs;
//        }
//    }

    public void appendTime(String visitTime) {

        try {
            Date addTime = timeFormat.parse(visitTime.substring(11, 19));
            if (timeFrom.after(addTime)) timeFrom = addTime;
            if (timeTo.before(addTime)) timeTo = addTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
//        if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(visitTime.getTime()))))
//            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
//        long visitTimeTs = visitTime.getTime();
//        if(visitTimeTs < from) {
//            from = visitTimeTs;
//        }
//        if(visitTimeTs > to) {
//            to = visitTimeTs;
//        }
    }


    public String toString() {
        return date + " : " + timeFormat.format(timeFrom) + "-" + timeFormat.format(timeTo);
    }

    @Override
    public int compareTo(TimePeriod period) {

        if (date.equals(period.date.substring(0, 10))) return 0;
        return 1;

    }
}
