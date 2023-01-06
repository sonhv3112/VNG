package vng.training.w4.slackfake.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.*;
import vng.training.w4.slackfake.server.PacketResolver;

import java.nio.ByteBuffer;

@Log4j2
@AllArgsConstructor
@Component
public class MessageHandler extends SimpleChannelInboundHandler<RequestPacket> {
    private final PacketResolver packetResolver;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel incoming = ctx.channel();
        log.info("Server connect to: " + incoming.remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestPacket request) throws Exception {
//        log.info("Received request...");
//        switch (request.getDataCase()) {
//            case LOGIN:
//                loginHandler(ctx, request);
//                break;
//            case LOGOUT:
//                logoutHandler(ctx, request);
//                break;
//            case CREATECHANNEL:
//                createChannelHandler(ctx, request);
//                break;
//            case JOINCHANNEL:
//                joinChannelHandler(ctx, request);
//                break;
//            case LISTINGCHANNEL:
//                listingChannelHandler(ctx, request);
//                break;
//            case READMESSAGE:
//                readMessageHandler(ctx, request);
//                break;
//            case REGISTER:
//                registerHandler(ctx, request);
//                break;
//            case SENDMESSAGETOCHANNEL:
//                sendMessageToChannelHandler(ctx, request);
//                break;
//            default:
//                log.debug("Invalid request from server Netty!");
//                break;
//        }
        ResponsePacket response = packetResolver.resolve(request);;

        String result = request.toString().trim();
        log.info("From client: " + ctx.channel().remoteAddress());
        log.info("Message received: " + result);
        log.info("Sending response: " + response);

        ctx.writeAndFlush(response);
    }

//    private void registerHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received register request from " + ctx.channel().remoteAddress());
//    }
//
//    private void logoutHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received logout request from " + ctx.channel().remoteAddress());
//    }
//
//    private void createChannelHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received create channel request from " + ctx.channel().remoteAddress());
//    }
//
//    private void listingChannelHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received list channel request from " + ctx.channel().remoteAddress());
//    }
//
//    private void loginHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received login request from " + ctx.channel().remoteAddress());
//    }
//
//    private void joinChannelHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received join channel request from " + ctx.channel().remoteAddress());
//    }
//
//    private void sendMessageToChannelHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received send message request from " + ctx.channel().remoteAddress());
//    }
//
//    private void readMessageHandler(ChannelHandlerContext ctx, RequestPacket request) {
//        log.info("Received read message request from " + ctx.channel().remoteAddress());
//    }
}