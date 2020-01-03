import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Storage {

    private int CHUNK_SIZE = 1024;
    private long docSize;
    private long chunkCount;
    private long caretPos = 0;
    private AsynchronousFileChannel channel;
    private HashMap<Long, Chunk> chunkMap;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(CHUNK_SIZE);


    Storage (String path) {
        try {
            channel = AsynchronousFileChannel.open(Paths.get("."+path), StandardOpenOption.READ);
            chunkMap = new HashMap<>();
            docSize = channel.size();
            chunkCount = docSize/CHUNK_SIZE > 0 ? (docSize/CHUNK_SIZE) : 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void close() {
        try {
            channel.close();
            System.out.println("channel is Closed: " + channel.isOpen());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    long getChunkCount() {
        return chunkCount;
    }
    //=================================================================






    private void shiftForward(long pos) {

        caretPos += pos;
        if (caretPos> docSize) {
            caretPos = docSize;
        }
        System.out.println("SHIFTFORWARD to " + caretPos);
    }
    void shiftBackward() {
        if (caretPos > CHUNK_SIZE*2) {
            caretPos = ((caretPos - (CHUNK_SIZE * 2))/CHUNK_SIZE)*CHUNK_SIZE; // с рассчётом чтобы не сбить куски прокручивая с последнего эл-та
        } else {
            caretPos = 0;
        }
        System.out.println("ShiftBackward to = " + caretPos);
    }
    void shiftToPercent (double percent) {
        caretPos = new BigDecimal(docSize*percent).longValue();
        caretPos = (caretPos/CHUNK_SIZE)* CHUNK_SIZE;
        System.out.println("Shift to PERCENT %% " + caretPos + "\t" + percent);
    }

    Chunk getChunk() {
        if (chunkMap.get(caretPos/CHUNK_SIZE) == null && docSize-caretPos > CHUNK_SIZE) {
            System.out.println("reading chunk " + caretPos/CHUNK_SIZE);
            try {
                return read();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            if(docSize-caretPos < CHUNK_SIZE) return chunkMap.get((docSize/CHUNK_SIZE)-1);
            System.out.println("getting chunk"  + caretPos/CHUNK_SIZE);
            Chunk chunk = chunkMap.get(caretPos/CHUNK_SIZE);
            shiftForward(caretPos < docSize ? CHUNK_SIZE : 0);
            return chunk;
        }
        return null;
    }


    Chunk read() throws ExecutionException, InterruptedException {

        long chunkNum = caretPos / CHUNK_SIZE;
        Future<Integer> f = channel.read(byteBuffer, caretPos);
        shiftForward(f.get());
        String result = new String(byteBuffer.array());
        if (chunkNum > 0) { // cut beginning str
            result = result.substring(result.indexOf(10));
        }
        if (caretPos < docSize) {
            result += getNLine();

        }
        Chunk chunk = new Chunk(result);
        chunkMap.put(chunkNum, chunk);
        byteBuffer.clear();
        return chunk;


    }
    String getNLine() throws ExecutionException, InterruptedException {

        if (docSize - caretPos > CHUNK_SIZE) {
            ByteBuffer b = ByteBuffer.allocate(150);
            channel.read(b, caretPos).get();
            String res = new String(b.array());
            b.clear();
            return res.substring(0, res.indexOf(10));
        } else {
            ByteBuffer b = ByteBuffer.allocate(CHUNK_SIZE);
            shiftForward(channel.read(b, caretPos).get());
            String res = new String(b.array()) + "\n\nEnd.";
            b.clear();
            return res;
        }
    }
}
