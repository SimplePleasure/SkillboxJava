public class DataManager {

    final DataManager monitor = this;
    //private static final Object monitor = new Object();

    private static boolean ready = false;


    public void prepareData() {
        synchronized (monitor) {
            System.out.println("Data prepared");
            ready = true;
            monitor.notify();
        }
    }

    public void sentData() {
        synchronized (monitor) {
            System.out.println("Waiting for data...");
            if (!ready) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // continue execution and sending data
            System.out.println("Sending data...");
        }
    }


}
















//public class DataManager {
//    private static final Object monitor = new Object();
//    private boolean ready = false;
//
//    public void sentData() {
//        synchronized (monitor) {
//            System.out.println("Waiting for Data...");
//
//            while (!ready) {
//                try {
//                    monitor.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("Sending Data...");
//        }
//    }
//    public void prepareData() {
//        synchronized (monitor) {
//            System.out.println("Data prepared.");
//            ready=true;
//            monitor.notifyAll();
//        }
//    }
//
//}


//


//
//public class DataManager {
//    //private static boolean ready = false;
//    private static final Object lock = new Object();
//
//    public  void sentData() {
//        synchronized (lock){
//            // waiting
//            System.out.println("Waiting for data...");
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // continue execution and sending data
//        System.out.println("Data sended.");
//    }
//
//    public void prepareData() {
//        synchronized (lock) {
//            System.out.println("Data prepared");
//            //ready = true;
//            lock.notifyAll();
//        }
//    }
//}