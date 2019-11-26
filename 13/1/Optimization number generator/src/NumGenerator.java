import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class NumGenerator
{


    public void createNumbers() throws IOException {



        try(FileOutputStream fos = new FileOutputStream("res/carNum.txt");
            PrintWriter out = new PrintWriter(fos)) {
            long startGenerate = System.currentTimeMillis();
            StringBuilder buffer = new StringBuilder();
            int bufferSize = 1_000_000;

            for (int reg: GenericOrder.region) {
                for (int num = 1; num < 1000; num++) {
                    for (char ch1 : GenericOrder.letters) {
                        for (char ch2 : GenericOrder.letters) {
                            for (char ch3 : GenericOrder.letters) {

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
