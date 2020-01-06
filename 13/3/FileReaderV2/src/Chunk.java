import jdk.swing.interop.SwingInterOpUtils;

import java.nio.ByteBuffer;

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
        ByteBuffer buffer = ByteBuffer.allocate(Storage.CHUNK_SIZE * 2).put(text.getBytes());
        buffer.flip();
        buffer.clear();
        return buffer;
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
