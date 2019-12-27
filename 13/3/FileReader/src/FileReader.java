import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReader {

    StringBuilder buffer;
    RandomAccessFile file;
    long position;

    FileReader (String path) {
        try {
            file = new RandomAccessFile(path, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder read(long pos) {

        buffer = new StringBuilder();
        try {
            file.seek(pos);

            int ch = file.read();
            while (ch != -1) {

                buffer.append((char)ch);
                if (buffer.length() > 2500 && ch == 10 ){
                    position = file.getFilePointer();
                    break;
                }
                ch = file.read();
            }
        } catch (IOException e) {}
        return buffer;
    }

    long shiftLine(long position) {
        long pos = 0;
        if (position >= 0) {
            try {
                file.seek(position);
                file.readLine();
                pos = file.getFilePointer();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pos;
    }


    public long getPosition() {
        return position;
    }

}
