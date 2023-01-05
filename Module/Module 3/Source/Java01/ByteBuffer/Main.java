package ByteBuffer;

import java.util.List;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) { 
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(3);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(4);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(2);
 
        byteBuffer1.put((byte)20);
        byteBuffer1.put((byte)30);
        byteBuffer2.put((byte)40);
        byteBuffer3.put((byte)50);

        System.out.println("ByteBuffer 1: " + Arrays.toString(byteBuffer1.array()));
        System.out.println("ByteBuffer 2: " + Arrays.toString(byteBuffer2.array()));
        System.out.println("ByteBuffer 3: " + Arrays.toString(byteBuffer3.array()));

        ByteBuffer concatByteBuffer = ByteBufferUtils.join(byteBuffer1, byteBuffer2, byteBuffer3);
        System.out.println("concat ByteBuffer 1, 2, 3: " + Arrays.toString(concatByteBuffer.array()));

        List<ByteBuffer> byteBuffersList = new ArrayList<>(); 
        byteBuffersList.add(byteBuffer1); 
        byteBuffersList.add(byteBuffer2); 
        byteBuffersList.add(byteBuffer3); 

        ByteBuffer joinByteBufferList = ByteBufferUtils.join(byteBuffersList);
        System.out.println("join ByteBuffer list: " + Arrays.toString(joinByteBufferList.array()));
    }
}