package birds;

import java.util.Comparator;

public class VerebrateComporator implements Comparator<Verebrate> {

    @Override
    public int compare(Verebrate o1, Verebrate o2) {
        return (int)Math.round(o1.getWeight()-o2.getWeight());
    }
}
