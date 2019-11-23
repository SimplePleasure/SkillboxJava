/**
 * Created by Danya on 18.02.2016.
 */
public class Friend implements Comparable<Friend>
{
    private final String name;
    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void throwBallTo(Friend catcher) {

        System.out.format("%s: %s has thrown to me!%n", catcher.getName(), this.name);
//        if (catcher.compareTo(this) > 0) {
//            synchronized (catcher) {
//                synchronized (this) {
                    catcher.throwBallTo(this);
//                }
//            }
//        } else {
//            synchronized (this) {
//                synchronized (catcher) {
//                    catcher.throwBallTo(this);
//                }
//            }
//        }
    }

    @Override
    public int compareTo(Friend o) {
        return this.getName().compareTo(o.getName());
    }
}
