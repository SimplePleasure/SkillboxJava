import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.concurrent.*;

public class Storage {

    protected static int CHUNK_SIZE = 1024;
    private long docSize;
    private long caretPos;
    private long chunkCount;
    private Chunk lastViewed;
    private AsynchronousFileChannel channel;
    private HashMap<Long, Chunk> chunkStorage;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(CHUNK_SIZE);
//    private ExecutorService ex;


    Storage (String path) {
        try {
            channel = AsynchronousFileChannel.open(Paths.get("."+path), StandardOpenOption.READ, StandardOpenOption.WRITE);
            chunkStorage = new HashMap<>();
            docSize = channel.size();
            chunkCount = docSize/CHUNK_SIZE > 0 ? (docSize/CHUNK_SIZE) : 1;
            caretPos = 0;
//            ex = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void close() {
        try {
//            ex.shutdown();
//            ex.awaitTermination(1, TimeUnit.MINUTES);
            channel.close();
            System.out.println("New channel is open");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    long getChunkCount() {
        return chunkCount;
    }
    //=================================================================





    void reWriteText(Chunk chunk) {


//        channel.write(chunk.getBufferedText(), (chunk.getNum()*CHUNK_SIZE)+chunk.getShift(),
//                                                chunk.getNum(), new CompletionHandler<Integer, Long>() {
//            @Override
//            public void completed(Integer result, Long attachment) {
//                chunk.setLoaded();
//                System.out.println("Chunk " + attachment + " was sucsesfully rewtite.");
//            }
//
//            @Override
//            public void failed(Throwable exc, Long attachment) {
//                System.out.println("Chunk " + attachment + " doesn't rewrite.");
//                exc.printStackTrace();
//            }
//        });

        channel.write(chunk.getBufferedText(), (chunk.getNum()*CHUNK_SIZE)+ chunk.getShift());
        chunk.setLoaded();
        chunkStorage = new HashMap<>();
    }



    Chunk getChunk() {

        try {
            if (chunkStorage.get(lastViewed.getNum()).chunkState == Chunk.ChunkState.DURTY) {
                System.out.println("\nredacting "+ lastViewed.getNum());
                reWriteText(lastViewed);
            }
        } catch (NullPointerException ignore) {}

        if (chunkStorage.get(caretPos/CHUNK_SIZE) == null && docSize-caretPos > CHUNK_SIZE) {
            System.out.println("reading chunk " + caretPos/CHUNK_SIZE);
            try {

                lastViewed = read();
                return lastViewed;

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {

            if (docSize-caretPos < CHUNK_SIZE) {
                return lastViewed = chunkStorage.get((caretPos/CHUNK_SIZE)-1);
            }
            lastViewed = chunkStorage.get(caretPos/CHUNK_SIZE);
            System.out.println("getting chunk "  + lastViewed.getNum());
            shiftForward(caretPos < docSize ? CHUNK_SIZE : 0);
            return lastViewed;
        }
        return null;
    }

    Chunk read() throws ExecutionException, InterruptedException {

        int shift=0;
        long chunkNum = caretPos / CHUNK_SIZE;
        Future<Integer> f = channel.read(byteBuffer, caretPos);
        shiftForward(f.get());
        String result = new String(byteBuffer.array());

        if (chunkNum > 0) { // cut beginning str
            shift = ByteBuffer.allocate(CHUNK_SIZE).put(result.substring(0, result.indexOf(10)).getBytes()).position();

            result = result.substring(result.indexOf(10));
        }
        if (caretPos < docSize) {
            result += supplementLine();

        }
        Chunk chunk = new Chunk(result, chunkNum, shift);
        chunkStorage.put(chunkNum, chunk);
        byteBuffer.clear();
        return chunk;


    }

    String supplementLine() throws ExecutionException, InterruptedException {

        if (docSize - caretPos > CHUNK_SIZE) {
            System.out.println("add piece of string");
            ByteBuffer b = ByteBuffer.allocate(150);

            channel.read(b, caretPos).get();
            String res = new String(b.array());
            res = res.substring(0, res.indexOf(10));
            b.clear();

            return res;
        } else {
            System.out.println("add remaining line");
            ByteBuffer b = ByteBuffer.allocate(CHUNK_SIZE);
            shiftForward(channel.read(b, caretPos).get());
            String res = new String(b.array());
            b.clear();
            return res;
        }
    }
// ===================================== Moving=========================================================================
    void shiftToPercent (double percent) {
        caretPos = new BigDecimal(docSize*percent).longValue();
        caretPos = (caretPos/CHUNK_SIZE)* CHUNK_SIZE;
        System.out.println("Shift to PERCENT %% " + caretPos + "\t" + percent);
    }

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
// =====================================================================================================================
}
