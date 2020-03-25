package PrintWorkTime.PrintWorkTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod implements Comparable<TimePeriod> {


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDate visitDate;
    LocalTime from;
    LocalTime to;

    TimePeriod(LocalDateTime visit) {
        visitDate = visit.toLocalDate();
        from = visit.toLocalTime();
        to = visit.toLocalTime();

    }


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void addTime(LocalTime time) {

        if (from.isAfter(time)) {
            from = time;
        } else if (to.isBefore(time)) {
            to = time;
        }

    }

    @Override
    public String toString() {
        return formatter.format(from) + " - " + formatter.format(to);
//        return from.getHour()+":"+from.getMinute() + " - " + to.getHour() + ":" + to.getMinute();
    }

    @Override
    public int compareTo(TimePeriod o) {
        if (visitDate.isAfter(o.getVisitDate()))  return 1;
        if (visitDate.isBefore(o.getVisitDate())) return -1;
        return 0;
    }
}
