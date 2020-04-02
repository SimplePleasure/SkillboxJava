package PrintWorkTime.PrintWorkTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class VoteStationWorkTime {


    TreeSet<TimePeriod> periods;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate first = LocalDate.parse("17.09.2015", formatter);
    LocalDate second = LocalDate.parse("19.09.2015", formatter);
    LocalDate third = LocalDate.parse("21.09.2015", formatter);


    VoteStationWorkTime() {
        periods = new TreeSet<>();
        periods.add(new TimePeriod(first));
        periods.add(new TimePeriod(second));
        periods.add(new TimePeriod(third));


    }

    public void addVisitTime(LocalDateTime visit) {
        for(TimePeriod period: periods) {
            if(period.getVisitDate().equals(visit.toLocalDate())) {
                period.addTime(visit.toLocalTime());
                return;
            }
        }
    }

    public TreeSet<TimePeriod> getPeriods() {
        return periods;
    }

}
