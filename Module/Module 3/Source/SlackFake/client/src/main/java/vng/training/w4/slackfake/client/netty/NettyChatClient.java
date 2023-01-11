package vng.training.w4.slackfake.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.client.ChatClient;
import vng.training.w4.slackfake.client.ReceiveListener;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.IOException;
import java.util.function.Consumer;

@Log4j2
@Component
public class NettyChatClient implements ChatClient {
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private Channel channel;

    @Override
    public void start(String host, int port) throws Exception {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                                new ProtobufVarint32FrameDecoder(),
                                new ProtobufDecoder(ResponsePacket.getDefaultInstance()),
                                new ProtobufVarint32LengthFieldPrepender(),
                                new ProtobufEncoder(),
                                new NettyChatClientHandler());
                    }
                });

        channel = bootstrap.connect(host, port).sync().channel();
    }

    @Override
    public void send(RequestPacket packet, Consumer<ResponsePacket> callback) throws IOException {
        channel.writeAndFlush(packet);
    }

    @Override
    public void addReceiveListener(ReceiveListener listener) {

    }

    @Override
    public void close() throws IOException {
        try {
            if (channel != null)
                channel.closeFuture().sync();
            if (group != null)
                group.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
