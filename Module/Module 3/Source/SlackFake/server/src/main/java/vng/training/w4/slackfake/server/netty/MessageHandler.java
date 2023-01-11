package vng.training.w4.slackfake.server.netty;

import com.google.common.collect.Multimap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.server.PacketResolver;
import vng.training.w4.slackfake.service.ChatUserConnection;
import vng.training.w4.slackfake.service.UserConnectionService;
import vng.training.w4.slackfake.tasks.PacketResolveState;

import java.util.concurrent.Future;

@Log4j2
@AllArgsConstructor
public class MessageHandler extends SimpleChannelInboundHandler<RequestPacket> {

    private final PacketResolver packetResolver;
    private final UserConnectionService userConnectionService;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel incoming = ctx.channel();
        log.info("Server connect to: " + incoming.remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestPacket request) throws Exception {
        String result = request.toString().trim();
        log.info("Message received from client \"" + ctx.channel().remoteAddress() + "\":\n" + result);

        PacketResolveState state = new PacketResolveState(request);
        Future<Void> future = packetResolver.resolve(state);
        future.get();

        final ResponsePacket response = state.get(PacketResolveState.RESPONSE_KEY);
        if (response == null) {
            return;
        }

        log.info("Send response to \"" + ctx.channel().remoteAddress() + "\":\n" + response);
        if (response.getIsAuthorized() && response.hasLoginResponse()) {
            ResponsePacket.Login data = response.getLoginResponse();
            if (data.getStatus() == ResponsePacket.Login.Status.SUCCESS) {
                ChatUserConnection connection = new NettyConnection(ctx, state.get(PacketResolveState.USER_ID_KEY));
                userConnectionService.register(connection);
                log.info("Login success: " + ctx.channel().remoteAddress());
            }
        }

        ctx.writeAndFlush(response);
        /*if (request.getDataCase() == RequestPacket.DataCase.SENDMESSAGETOCHANNEL) {
            if (response.getReadMessageResponse().getStatus() == ResponsePacket.ReadMessage.Status.SUCCESS) {
                Collection<ChannelHandlerContext> clientList = clientMap.get(request.getSendMessageToChannel().getChannelId());
                Iterator<ChannelHandlerContext> iter = clientList.iterator();
                while (iter.hasNext()) {
                    ChannelHandlerContext _ctx = iter.next();
                    boolean isActive = _ctx.channel().isActive();

                    if (!isActive) {
                        iter.remove();
                        log.debug("Remove \"{}\" from channel {}", _ctx.channel().remoteAddress(), request.getSendMessageToChannel().getChannelId());
                        continue;
                    }

                    _ctx.writeAndFlush(response);
                    log.info("Send response to \"" + _ctx.channel().remoteAddress() + "\":\n" + response);
                }
            }
        } else {
            ctx.writeAndFlush(response);
            log.info("Send response to \"" + ctx.channel().remoteAddress() + "\":\n" + response);
            if (response.getResponseCase() == ResponsePacket.ResponseCase.LISTINGCHANNELRESPONSE) {
                ResponsePacket.ListingChannel responseList = response.getListingChannelResponse();
                for (int i = 0; i < responseList.getChannelIdsCount(); ++i) {
                    clientMap.put(responseList.getChannelIds(i), ctx);
                }
            }
        }*/
    }
}
