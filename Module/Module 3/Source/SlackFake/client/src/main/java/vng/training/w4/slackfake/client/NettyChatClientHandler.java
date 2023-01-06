package vng.training.w4.slackfake.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vng.training.w4.slackfake.protobuf.*;

public class NettyChatClientHandler extends SimpleChannelInboundHandler<ResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponsePacket response) throws Exception {
        System.out.println("Received response from server: " + response.getRegisterResponse());
    }
}