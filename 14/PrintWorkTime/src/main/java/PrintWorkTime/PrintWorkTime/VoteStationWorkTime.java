package PrintWorkTime.PrintWorkTime;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class VoteStationWorkTime {


    static int dayOne = 17;
    static int dayTwo = 19;
    static int three = 21;

    public TreeSet<TimePeriod> getPeriods() {
        return periods;
    }

    TreeSet<TimePeriod> periods;

    VoteStationWorkTime() {
        periods = new TreeSet<>();
    }

    public void addVisitTime(LocalDateTime visit) {



        for(TimePeriod period: periods) {
            if(period.getVisitDate().equals(visit.toLocalDate())) {
                period.addTime(visit.toLocalTime());
                return;
            }
        }
        periods.add(new TimePeriod(visit));

    }

    @Override
    public String toString() {
        String station = "";
        int workDay = 0;
        for (TimePeriod p : periods) {
            workDay++;
            if (workDay == 1 && p.getVisitDate().getDayOfMonth() == dayOne) {
                station += p.toString() + "</td>";
            } else if (workDay==1 && p.getVisitDate().getDayOfMonth()!=dayOne) {
                station += "</td> <td>" + p.toString() + "</td>";
                workDay++;
            } else if (workDay == 2 && p.getVisitDate().getDayOfMonth() == dayTwo) {
                station += "<td>" + p.toString() +"</td>";
            } else if (workDay == 2 && p.getVisitDate().getDayOfMonth() != dayTwo) {
                station += "<td></td><td>" + p.toString();
                workDay = 0;
            } else if (workDay == 3) {
                station += "<td>" + p.toString();
                workDay = 0;
            }
        }
        return station;
    }
}
