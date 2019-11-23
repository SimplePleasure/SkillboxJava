import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NumGenerator
{


    public void createNumbers() throws IOException {

        char[] letters = {'a', 'в', 'е', 'и', 'к', 'м', 'н', 'о', 'р', 'с', 'т', 'х'};
        int[] region = {47, 78, 98, 147, 178, 198, 77, 99, 199, 750};



        try(FileOutputStream fos = new FileOutputStream("res/carNum.txt");
            PrintWriter out = new PrintWriter(fos)) {
            long startGenerate = System.currentTimeMillis();
            StringBuilder buffer = new StringBuilder();
            int bufferSize = 1_000_000;

            for (int reg: region) {
                for (int num = 1; num < 1000; num++) {
                    for (char ch1 : letters) {
                        for (char ch2 : letters) {
                            for (char ch3 : letters) {

                                if(buffer.length() > bufferSize) {
                                    out.write(buffer.toString());
                                    buffer = new StringBuilder();
                                }

                                buffer.append(ch1);
                                if (num <10) {
                                    buffer.append("00");
                                }else if (num < 100) {
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
            }
            out.write(buffer.toString());
            fos.getChannel().force(true);
            System.out.println(System.currentTimeMillis()-startGenerate);
        }
    }



    public static void main(String[] args) throws IOException {
        NumGenerator n = new NumGenerator();
        n.createNumbers();
    }


}
