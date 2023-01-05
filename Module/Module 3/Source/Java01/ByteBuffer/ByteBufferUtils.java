package ByteBuffer;

import java.util.List;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class ByteBufferUtils {
    public static ByteBuffer join(List<ByteBuffer> buffers){
        int totalSize = buffers.stream().mapToInt(ByteBuffer::limit).sum();
        ByteBuffer result = ByteBuffer.allocate(totalSize);

        for (ByteBuffer buffer : buffers) {
            result.put(buffer.array());
        }

        result.flip();
        return result;
    }

    public static ByteBuffer join(ByteBuffer... buffers){
        int totalSize = 0;
        for (ByteBuffer buffer : buffers) {
            totalSize += buffer.limit();
        }

        ByteBuffer result = ByteBuffer.allocate(totalSize);
        for (ByteBuffer buffer : buffers) {
            result.put(buffer.array());
        }

        result.flip();
        return result;
    }
}
