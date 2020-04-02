package PrintWorkTime.PrintWorkTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod implements Comparable<TimePeriod> {


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public LocalDate visitDate;
    LocalTime from;
    LocalTime to;

    TimePeriod(LocalDate visit) {
        visitDate = visit;
    }


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void addTime(LocalTime time) {
        if(from == null) {
            from = time;
            to = time;
            return;
        }

        if (from.isAfter(time)) {
            from = time;
        } else if (to.isBefore(time)) {
            to = time;
        }
    }

    @Override
    public String toString() {
        if (from == null && to == null) {
            return "";
        }
        return formatter.format(from) + " - " + formatter.format(to);
    }

    @Override
    public int compareTo(TimePeriod o) {
        if (visitDate.isAfter(o.getVisitDate()))  return 1;
        if (visitDate.isBefore(o.getVisitDate())) return -1;
        return 0;
    }
}
