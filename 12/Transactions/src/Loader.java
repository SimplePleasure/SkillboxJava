import java.io.IOException;


public class Loader {

    public static void main(String[] args) throws IOException {

        Bank b = new Bank();



        for (int i=0; i<50; i++) {
            int amount = 1500*(i+1);


            Thread th1= new Thread(new Runnable() {
                @Override
                public void run() {
                    b.transfer("1", "2", amount);
                }
            });
            th1.start();
            try {
                th1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Thread th2= new Thread(new Runnable() {
                @Override
                public void run() {
                    b.transfer("2", "3", amount);
                }
            });
            th2.start();
            try {
                th2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Thread th3= new Thread(new Runnable() {
                @Override
                public void run() {
                   b.transfer("3", "1", amount);
                }
            });
            th3.start();
            try {
                th3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("balance 1:\t"+b.getBalance("1") + "\nbalance 2:\t" + b.getBalance("2") +
            "\nbalance 3:\t"+b.getBalance("3"));
    }

}
