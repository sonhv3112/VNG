package vng.training.w4.slackfake.server.netty;

import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.ChatUserConnection;

import java.io.IOException;

@AllArgsConstructor
public class NettyConnection implements ChatUserConnection {

    private final ChannelHandlerContext context;
    private final String userId;

    @SneakyThrows
    @Override
    public void close() throws IOException {
        context.close().get();
    }

    @Override
    public boolean isClosed() {
        return !context.channel().isActive();
    }

    @Override
    public void send(ResponsePacket packet) throws IOException {
        context.writeAndFlush(packet);
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getAddress() {
        return context.channel().remoteAddress().toString();
    }

}
