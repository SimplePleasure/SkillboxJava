package PrintWorkTime.PrintWorkTime;

import java.text.SimpleDateFormat;
import java.util.TreeSet;

public class VoteStationWorkTime {


    static SimpleDateFormat tf = new SimpleDateFormat("hh:mm");
    TreeSet<TimePeriod> periods;

    VoteStationWorkTime() {
        periods = new TreeSet<>();
    }

    public void addVisitTime(String visit) {


        String visitDate = visit.substring(0, 10);
        for(TimePeriod period: periods) {
            if(period.getVisitDate().equals(visitDate)) {
                period.addTime(visit.substring(11,19));
                return;
            }
        }
        periods.add(new TimePeriod(visit));
    }

    @Override
    public String toString() {
        String station = "";
        for (TimePeriod p : periods) {
            station += p.getVisitDate() + ": " + tf.format(p.from) + "-" + tf.format(p.to) + "\t";
        }
        return station;
    }
}
