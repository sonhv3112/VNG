package vng.training.w4.slackfake.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.server.AppPacketResolver;
import vng.training.w4.slackfake.server.ChatServer;
import vng.training.w4.slackfake.server.PacketResolver;

import java.io.IOException;
@Log4j2
@AllArgsConstructor
@Component
public class NettyServer implements ChatServer {
    private final PacketResolver packetResolver;

    @Override
    public void start(String host, int port) throws Exception {
        log.info("Netty server is running at port: " + port);

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new ProtobufVarint32FrameDecoder(),
                                    new ProtobufDecoder(RequestPacket.getDefaultInstance()),
                                    new ProtobufVarint32LengthFieldPrepender(),
                                    new ProtobufEncoder(),
                                    new MessageHandler(packetResolver));
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
