/**
 * Created by Danya on 18.02.2016.
 */
public class Loader
{
    public static void main(String[] args)  {
        final Friend vasya = new Friend("Vasya");
        final Friend misha = new Friend("Misha");

        new Thread(new Runnable() {
            public void run() {
                vasya.throwBallTo(misha);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                misha.throwBallTo(vasya);
            }
        }).start();
    }

}
