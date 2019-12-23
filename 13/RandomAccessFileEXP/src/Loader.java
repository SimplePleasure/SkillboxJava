import java.io.File;
import java.io.IOException;

public class Loader {

    public static void main(String[] args) throws Exception {
        AccFile acc = new AccFile("res/test.txt");

        String s = "@Test\n" +
                "public void givenFilePath__whenReadsContentWithFuture__thenCorrect() {\n" +
                "    Path path = Paths.get(\n" +
                "      URI.create(\n" +
                "        this.getClass().getResource(\"/file.txt\").toString()));";

//        acc.write(s, 0);
//        acc.readFrom(0);


//        ====Либо UTF read/write=======================================
        acc.writeUTF(s, 0);
        acc.readFrom(0);
//        ==============================================================






    }
}
