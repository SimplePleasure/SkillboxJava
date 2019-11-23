public class Loader {

    public  static void  main (String[] args) {

        DataManager manager = new DataManager();


        new Thread(new Runnable() {
            @Override
            public void run() {
                manager.sentData();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                manager.prepareData();
            }
        }).start();
        


    }
}
