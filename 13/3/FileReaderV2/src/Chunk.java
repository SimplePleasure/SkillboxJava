import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

public class Chunk {

    private int shift;
    private String text;
    private long chunkNum;
    ChunkState chunkState;

    Chunk (String t, long p, int s) {

        text = t;
        shift = s;
        chunkNum = p;
        chunkState = ChunkState.LOADED;
    }

    enum ChunkState {
        NOT_LOADED,
        LOADING,
        LOADED,
        DURTY
    }

    long getNum() {
        return chunkNum;
    }
    int getShift() {
        return shift;
    }

    String getText() {
        return  text;
    }

    ByteBuffer getBufferedText() {
        byte[] bytes = text.getBytes();
//        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
//        buffer.flip();
//        buffer.clear();

        return ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8));
    }

    void checkUpdate(String t) {
        if (!t.equals(text)) {
            text = t;
            chunkState = ChunkState.DURTY;
            System.out.println("chunck state was edited");
        }
    }

    void setLoaded() {
        chunkState = ChunkState.LOADED;
    }


}
