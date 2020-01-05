import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ChunkStorage {

    private int CHUNK_SIZE = 1024;
    private long docSize;
    private long chunkCount;
    private long caretPos = 0;
    private AsynchronousFileChannel channel;
    private HashMap<Long, Chunk> chunkMap;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(CHUNK_SIZE);


//  =========================================================== Channel open/close =====================================
    ChunkStorage (String path) {
        try {
            channel = AsynchronousFileChannel.open(Paths.get("." + path),
                    StandardOpenOption.READ, StandardOpenOption.WRITE);
            chunkMap = new HashMap<>();
            docSize = channel.size();
            chunkCount = docSize/CHUNK_SIZE > 0 ? (docSize/CHUNK_SIZE)+1 : 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void close() {
        try {
            channel.close();
            System.out.println("channel isOpen: " + channel.isOpen());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    long getChunkCount() {
        return chunkCount;
    }
//  ====================================================================================================================



    private void shiftForward(long caretPos) {
        this.caretPos += caretPos;
    }

    void shiftBackward() {
        if (caretPos > CHUNK_SIZE*2) {
            caretPos -= CHUNK_SIZE * 2;
        } else {
            caretPos = 0;
        }
    }

    void shiftToPercent(double percent) {

        caretPos = new BigDecimal(docSize*percent).longValue();
        caretPos = (caretPos/CHUNK_SIZE)* CHUNK_SIZE;

        System.out.println("ShiftCaretForward ______________");
        System.out.println("%%%% " + percent);
        System.out.println("docSize " + docSize);
        System.out.println("caretPos: "+ caretPos);
        System.out.println("_________________________________\n");
    }

//    Chunk getChunk() {
//
//        long chunkNum = (caretPos + CHUNK_SIZE) / CHUNK_SIZE;
//
//
//        if (docSize-caretPos > CHUNK_SIZE) {
//            System.out.println("Осталось прочитать > CHUNK_SIZE");
//            if (chunkMap.get(chunkNum) == null) {
//                try {
//                    Future<Integer> f = channel.read(byteBuffer, caretPos);
//                    shiftForward(f.get());
//
//                    System.out.println("Reading");
//                    System.out.println("caret: " + caretPos + "\t docSize: " + docSize);
//                    System.out.println("chunknum " + chunkNum);
//
//                    String result = new String(byteBuffer.array()).trim();
//                    if (chunkNum > 1) {
//                        result = result.substring(result.indexOf(10));
//                    }
//                    if (caretPos < docSize) {
//                        result = result + supplementLine();
//                    }
//                    chunkMap.put(chunkNum, new Chunk(result));
//                    byteBuffer.clear();
//                } catch (ExecutionException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (docSize - caretPos > CHUNK_SIZE) {
//                    shiftForward(CHUNK_SIZE);
//                } else {
//                    shiftForward(docSize - caretPos);
//                }
//            }
//        } else {
//            chunkNum -= 1;
//        }
//        System.out.println("return");
//        return chunkMap.get(chunkNum);
//    }

    Chunk getChunk() {

        long chunkNum = caretPos / CHUNK_SIZE;


        if (docSize-caretPos >= CHUNK_SIZE) {   //Если осталось прочитать больше 1 куска
            System.out.println("Осталось прочитать больше 1 куска");
            if (chunkMap.get(chunkNum) == null) {   // Читаем, если кусок ещё не прочитан.
                try {
                    Future<Integer> f = channel.read(byteBuffer, caretPos);
                    shiftForward(f.get());

                    System.out.println("Reading");
                    System.out.println("caret: " + caretPos + "\t docSize: " + docSize);
                    System.out.println("chunknum " + chunkNum);

                    String result = new String(byteBuffer.array()).trim();
                    if (chunkNum > 0) {  // Обрезаем начало если это не первый кусок.
                        result = result.substring(result.indexOf(10));
                    }
                    if (caretPos < docSize) {  // Дополняем кусок до новой строки.
                        result = result + getNLine();
                    }
//                    chunkMap.put(chunkNum, new Chunk(result, docSize/caretPos));
                    byteBuffer.clear();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {    //Если кусок прочитан.
                if (docSize - caretPos > CHUNK_SIZE) {  // Сдвигаем каретку на размер байтбуфера
                    shiftForward(CHUNK_SIZE);
                } else {
                    shiftForward(docSize - caretPos);
                }
            }
        } else {
            System.out.println("chunknum-1, return");
            return chunkMap.get(chunkNum-1);
        }
        System.out.println("return");
        return chunkMap.get(chunkNum);
    }

    String getNLine() {
        if (docSize-caretPos > CHUNK_SIZE) {
            ByteBuffer buf = ByteBuffer.allocate(100);
            try {
                channel.read(buf, caretPos).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            String lineAfter = new String(buf.array());
            lineAfter = lineAfter.substring(0, lineAfter.indexOf(10)).trim();
            return lineAfter;
        } else {
            try {
                ByteBuffer buf = ByteBuffer.allocate(CHUNK_SIZE);
                channel.read(buf, caretPos).get();
                shiftForward(docSize-caretPos);
                return new String(buf.array()).trim();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
