package vng.training.w4.slackfake.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@Component
public class NIOChatClient implements ChatClient {

    private final AtomicReference<SocketChannel> clientRef = new AtomicReference<>();

    @Override
    public void start(String host, int port) throws IOException {
        synchronized (clientRef) {
            if (clientRef.get() != null) {
                return;
            }
            try {
                InetSocketAddress addr = new InetSocketAddress("localhost", 1108);
                SocketChannel client = SocketChannel.open(addr);
                clientRef.set(client);
            } catch (Exception e) {
                log.error("Cannot connect to server", e);
            }
        }
    }

    @Override
    public ResponsePacket send(RequestPacket packet) throws IOException {
        try {
            SocketChannel client = clientRef.get();
            if (client == null) {
                throw new IllegalStateException("Client is not started");
            }
            ByteBuffer buffer = ByteBuffer.wrap(packet.toByteArray());
            client.write(buffer);
            buffer.clear();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int numRead;
            while ((numRead = client.read(readBuffer)) > 0) {
                baos.write(readBuffer.array(), 0, numRead);
                readBuffer.clear();
            }
            return ResponsePacket.parseFrom(baos.toByteArray());
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void close() throws IOException {
        synchronized (clientRef) {
            SocketChannel client = clientRef.get();
            if (client != null) {
                client.close();
            }
        }
    }
}
