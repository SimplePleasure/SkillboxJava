package PrintWorkTime.PrintWorkTime;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class VoteStationWorkTime {

    public TreeSet<TimePeriod> periods;

    VoteStationWorkTime() {
        periods = new TreeSet<>();
        periods.add(new TimePeriod(WorktimeController.dayOne));
        periods.add(new TimePeriod(WorktimeController.dayTwo));
        periods.add(new TimePeriod(WorktimeController.dayThree));
    }

    public void addVisitTime(LocalDateTime visit) {
        for(TimePeriod period: periods) {
            if(period.getVisitDate().equals(visit.toLocalDate())) {
                period.addTime(visit.toLocalTime());
                return;
            }
        }
    }
}
