public class Chunk {

    private String text;
    ChunkState chunkState;

    Chunk (String t) {
        text = t;
        chunkState = ChunkState.LOADING;
    }

    enum ChunkState { NOT_LOADED,
                LOADING,
                LOADED,
                DURTY
    }

    String getText() {
        return  text;
    }


}
