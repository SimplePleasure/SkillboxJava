package PrintWorkTime.PrintWorkTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class VoteStationWorkTime {

    public TreeSet<TimePeriod> periods;

    VoteStationWorkTime(TreeSet<LocalDate> dayList) {
        periods = new TreeSet<>();
        for (LocalDate date : dayList) {
            periods.add(new TimePeriod(date));
        }
    }

    public void addVisitTime(LocalDateTime visit) {
        for(TimePeriod period: periods) {
            if(period.getVisitDate().equals(visit.toLocalDate())) {
                period.addTime(visit.toLocalTime());
                return;
            }
        }
    }

    public void addNewTimePeriod(LocalDate date) {
        periods.add(new TimePeriod(date));
    }
}
