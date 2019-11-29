import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class GenericOrder {

    static char[] letters = {'a', 'в', 'е', 'к', 'м', 'н', 'о', 'р', 'с', 'у', 'т', 'х'};
    static int[] region = {47, 78, 98, 147, 178, 198, 77, 99, 199, 750};

    LinkedBlockingDeque<StringBuilder> deque;
    String POSION_PILL;

    GenericOrder(LinkedBlockingDeque<StringBuilder> deque, String posionPill) {
        this.deque = deque;
        POSION_PILL = posionPill;
    }

    void generate() {
        ExecutorService ex = Executors.newFixedThreadPool(2);

        try {
            StringBuilder buffer = new StringBuilder();
            for (int reg : region) {

                for (int num = 1; num < 1000; num++) {
                    for (char ch1 : letters) {
                        for (char ch2 : letters) {
                            for (char ch3 : letters) {

                                buffer.append(ch1);
                                //.append(String.format("%03d", num))
                                if (num < 10) {
                                    buffer.append("00");
                                } else if (num < 100) {
                                    buffer.append("0");
                                }
                                buffer.append(num)
                                        .append(ch2)
                                        .append(ch3)
                                        .append(reg)
                                        .append("\n");


                                if (buffer.length() > 1_000_000) {
                                    deque.put(buffer);
                                    buffer = new StringBuilder();
                                }
                            }
                        }
                    }
                }
            }
            deque.put(buffer);
            buffer = new StringBuilder();
            buffer.append(POSION_PILL);
            deque.put(buffer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
