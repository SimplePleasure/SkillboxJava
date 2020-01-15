package File;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReaderLargeFiles {
    //result of reading file to view in textArea
    private String result;
    private RandomAccessFile file;
    //pointer, begin of string
    private long startPointer = 0;

    public ReaderLargeFiles(File f) throws IOException {
        //open file to read
        file = new RandomAccessFile(f.getPath(), "r");
    }

    //place pointer in file
    public void movePointer(long num) {
        if (num > getFileLength()) {
            num = getFileLength();
        }
        try {
            num = num < 0 ? 0 : num;
            file.seek(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startPointer = num;
    }

    public void readFileLines(int lines) {
        try {
            setPointerToBeginOfLine();
            readFromPointerPosition(lines);
        } catch (IOException e) {
            e.printStackTrace();
            result = "===\nОшибка чтения файла!!!\n===";
        }


    }

    //search begin of line to set pointer on it
    private void setPointerToBeginOfLine() throws IOException {
        //we are in start of file - go away
        if (file.getFilePointer() == 0) return;
        if (file.getFilePointer() < 0) {
            file.seek(0);
            return;
        }
        //we are on the end of line - go away
        if (file.read() == 10) {
            return;
        }
        //check, if we are on the begin of line
        file.seek(file.getFilePointer() - 1);
        if (file.read() == 10) {
            return;
        }
        //search begin of string in other ways (for backward search)
        int symbol;
        for (; ; ) {
            file.seek(file.getFilePointer() - 2);
            if (file.getFilePointer() <= 0) {
                file.seek(0);
                break;
            }
            symbol = file.read();
            //поиск перехода на новую строку
            if (symbol == 10) {
                break;
            }
        }
        startPointer = file.getFilePointer();
    }

    public long getFileLength() {
        int l = 0;
        try {
            l = (int) file.length() - 50;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    // One Screen = 26 lines
    public void readFromPointerPosition(int lineNumber) throws IOException {
        file.seek(startPointer);
        if (lineNumber > 0) {
            for (int i = 0; i < lineNumber; i++) {
                if (startPointer > getFileLength()) {
                    startPointer = getFileLength();
                    break;
                }
                file.readLine();
            }
        } else if (lineNumber < 0) {
            for (int i = 0; i > lineNumber; i--) {
                if ((file.getFilePointer() - 2) <= 0) {
                    file.seek(0);
                    break;
                }
                file.seek(file.getFilePointer() - 2);
                setPointerToBeginOfLine();
            }

        }
        startPointer = file.getFilePointer();
        result = readFromPointer();
    }

    //read file from pointer position to withdraw on screen
    //startPointer do not change!
    private String readFromPointer() throws IOException {
        StringBuilder result = new StringBuilder();
        file.seek(startPointer);
        for (int i = 0; i < 26; i++) {
            String line = file.readLine();
            if (line == null) {
                break;
            }
            line = new String(line.getBytes("ISO-8859-1"), "UTF8");
            result.append(line);
            result.append("\n");
        }
        file.seek(startPointer);
        return result.toString();
    }


    public String getResult() {
        return result;
    }

    public int getPointer() {
        int pointer = 0;
        try {
            pointer = (int) file.getFilePointer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pointer;
    }
}
