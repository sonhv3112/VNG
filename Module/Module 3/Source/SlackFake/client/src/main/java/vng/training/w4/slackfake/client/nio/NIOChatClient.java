package vng.training.w4.slackfake.client.nio;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.client.ChatClient;
import vng.training.w4.slackfake.client.ReceiveListener;
import vng.training.w4.slackfake.client.gui.ResponseHandler;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Log4j2
@Component
public class NIOChatClient implements ChatClient {

    private final AtomicReference<SocketChannel> clientRef = new AtomicReference<>();
    private ExecutorService receiveThread;
    private final Map<Integer, Consumer<ResponsePacket>> dynamicListeners = Maps.newConcurrentMap();
    private final LinkedList<Consumer<ResponsePacket>> listeners = new LinkedList<>();

    @Override
    public void start(String host, int port) throws IOException {
        synchronized (clientRef) {
            if (clientRef.get() != null) {
                return;
            }
            try {
                InetSocketAddress addr = new InetSocketAddress(host, port);
                SocketChannel client = SocketChannel.open(addr);
                clientRef.set(client);
                if (receiveThread != null) {
                    receiveThread.shutdownNow();
                }
                receiveThread = Executors.newSingleThreadExecutor();
                receiveThread.execute(() -> {
                    log.info("NIO Client. I'm a client and I'm waiting for new messages from server...");
                    try {
                        readAndHandle(client);
                    } catch (IOException e) {
                        log.error("Error while receiving packet", e);
                    }
                });
                receiveThread.shutdown();
            } catch (Exception e) {
                log.error("Cannot connect to server", e);
            }
        }
    }

    private void readAndHandle(SocketChannel client) throws IOException {
        while (client.isOpen()) {
            ResponsePacket packet = read(client);
            if (packet == null) {
                continue;
            }
            log.info("Received: {}", packet);
            ResponseHandler.handleResponse(packet);

            if (packet.hasPacketId()) {
                Consumer<ResponsePacket> listener = dynamicListeners.remove(packet.getPacketId());
                if (listener != null) {
                    listener.accept(packet);
                } else {
                    log.warn("No listener for packet id {}", packet.getPacketId());
                }
            } else {
                for (Consumer<ResponsePacket> listener : listeners) {
                    listener.accept(packet);
                }
            }
        }
        log.info("Client is closed");
    }

    public void send(RequestPacket packet, Consumer<ResponsePacket> callback) throws IOException {
        try {
            SocketChannel client = clientRef.get();
            if (client == null) {
                throw new IllegalStateException("Client is not started");
            }
            ByteBuffer buffer = ByteBuffer.wrap(packet.toByteArray());
            if (callback != null) {
                dynamicListeners.put(packet.getPacketId(), callback);
            }
            client.write(buffer);
        } catch (IOException e) {
            log.error("Cannot send packet", e);
            dynamicListeners.remove(packet.getPacketId());
            throw e;
        }
    }

    private ResponsePacket read(SocketChannel client) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        int read = 0;
        while ((read = client.read(buffer)) > 0) {
            if (buffer.remaining() == 0) {
                break;
            }
        }

        if (buffer.remaining() != 0) {
            return null;
        }

        buffer.flip();
        int length = buffer.getInt();

        buffer = ByteBuffer.allocate(length);

        while ((read = client.read(buffer)) > 0) {
            System.out.println(read);
            if (buffer.remaining() == 0) {
                break;
            }
        }

        if (buffer.remaining() != 0) {
            return null;
        }

        buffer.flip();
        return ResponsePacket.parseFrom(buffer);
    }

    @Override
    public void addReceiveListener(ReceiveListener listener) {
        throw new UnsupportedOperationException();
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
