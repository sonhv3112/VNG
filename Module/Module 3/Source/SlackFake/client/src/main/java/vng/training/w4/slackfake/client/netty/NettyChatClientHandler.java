package vng.training.w4.slackfake.client.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import vng.training.w4.slackfake.client.gui.ResponseHandler;
import vng.training.w4.slackfake.protobuf.*;

import java.util.List;

public class NettyChatClientHandler extends SimpleChannelInboundHandler<ResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponsePacket response) throws Exception {
        ResponseHandler.handleResponse(response);
    }
}