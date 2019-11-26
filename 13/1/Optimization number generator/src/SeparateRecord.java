import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SeparateRecord {


    void generate() {
        long startGenerate = System.currentTimeMillis();
        ExecutorService ex = Executors.newFixedThreadPool(4);

        for (int reg : GenericOrder.region) {
            ex.execute(() -> {
                StringBuilder buffer = new StringBuilder();

                try (FileOutputStream fos = new FileOutputStream("res/" + reg + ".txt");
                        PrintWriter pw = new PrintWriter(fos)) {

                    for (int num = 1; num < 1000; num++) {
                        for (char ch1 : GenericOrder.letters) {
                            for (char ch2 : GenericOrder.letters) {
                                for (char ch3 : GenericOrder.letters) {

                                    if (buffer.length() > 1000000) {
                                        pw.write(buffer.toString());
                                        buffer = new StringBuilder();
                                    }

                                    buffer.append(ch1);
                                    if (num < 10) {
                                        buffer.append("00");
                                    } else if (num < 100) {
                                        buffer.append("0");
                                    }
                                    buffer.append(num)
                                            .append(ch2)
                                            .append(ch3)
                                            .append(reg)
                                            .append('\n');
                                }
                            }
                        }
                    }
                    synchronized (this) {
                        pw.write(buffer.toString());
                    }

                    fos.getChannel().force(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            ex.shutdown();
            ex.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-startGenerate);

    }

    public static void main(String[] args) {
        SeparateRecord v = new SeparateRecord();
        v.generate();
    }
}
