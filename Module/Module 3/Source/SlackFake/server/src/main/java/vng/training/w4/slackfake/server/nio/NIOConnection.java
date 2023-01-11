package vng.training.w4.slackfake.server.nio;

import lombok.AllArgsConstructor;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.ChatUserConnection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static vng.training.w4.slackfake.server.nio.NIOServer.sendResponse;

@AllArgsConstructor
public class NIOConnection implements ChatUserConnection {

    private final NIOServer server;
    private final SocketChannel channel;
    private final String userId;

    @Override
    public void close() throws IOException {
        channel.close();
    }

    @Override
    public boolean isClosed() {
        return !channel.isOpen();
    }

    @Override
    public void send(ResponsePacket packet) throws IOException {
        sendResponse(channel, packet);
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getAddress() {
        return channel.socket().getInetAddress().toString();
    }

}
