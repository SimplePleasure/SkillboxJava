import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccFile {
    String path;
    RandomAccessFile file;



    AccFile(String path) {
        try {
            this.path = path;
            file = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(String str, long ch) throws IOException {
        //file = new RandomAccessFile(path, "rw");
        file.seek(ch);
        file.write(str.getBytes());
    }

    public void writeUTF(String str, int ch) throws IOException {
        //file = new RandomAccessFile(path, "rw");
        file.seek(ch);
        file.writeUTF(str);

    }
    public void readUTF(long ch) throws IOException {

        //file = new RandomAccessFile(path, "r");
        file.seek(ch);
        System.out.println(file.readUTF());

    }

    public void readFrom (long num) throws Exception {
        //file = new RandomAccessFile(path, "r");
        file.seek(num);
        String str = "";
        int ch = file.read();
        while (ch != -1) {
            str+= (char)ch;
            ch = file.read();
        }
        System.out.println(str);
    }
}
