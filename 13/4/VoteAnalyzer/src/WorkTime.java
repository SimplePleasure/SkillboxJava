import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Danya on 24.02.2016.
 */
public class WorkTime {
    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime() {
        periods = new TreeSet<>();
    }

    public void addVisitTime(String visitTime) {
        TimePeriod newPeriod = new TimePeriod(visitTime);
        for (TimePeriod period : periods) {
            if (period.compareTo(newPeriod) == 0) {
                period.appendTime(visitTime);
                return;
            }
        }
        periods.add(new TimePeriod(visitTime));
    }

    public String toString() {
        String line = "";
        for (TimePeriod period : periods) {
            if (!line.isEmpty()) {
                line += ", ";
            }
            line += period;
        }
        return line;
    }
}
