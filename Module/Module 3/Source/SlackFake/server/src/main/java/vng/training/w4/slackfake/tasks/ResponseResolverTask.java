package vng.training.w4.slackfake.tasks;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.channel.ChannelInputBoundary;
import vng.training.w4.slackfake.service.message.MessageInputBoundary;
import vng.training.w4.slackfake.service.user.UserInputBoundary;

@Component
public class ResponseResolverTask extends PacketResolveTask {

    private final UserInputBoundary userInputBoundary;
    private final MessageInputBoundary messageInputBoundary;
    private final ChannelInputBoundary channelInputBoundary;

    public ResponseResolverTask(UserInputBoundary userInputBoundary, MessageInputBoundary messageInputBoundary, ChannelInputBoundary channelInputBoundary) {
        this.userInputBoundary = userInputBoundary;
        this.messageInputBoundary = messageInputBoundary;
        this.channelInputBoundary = channelInputBoundary;
    }

    @Override
    protected void doAccept(PacketResolveState state) {
        RequestPacket req = state.get(PacketResolveState.REQUEST_KEY);
        ResponsePacket.Builder builder = (ResponsePacket.Builder) state.asMap().computeIfAbsent(PacketResolveState.RESPONSE_BUILDER_KEY, k -> ResponsePacket.newBuilder());
        RequestPacket.DataCase dataCase = req.getDataCase();
        builder.setPacketId(req.getPacketId());
        switch (dataCase.getNumber()) {
            case RequestPacket.REGISTER_FIELD_NUMBER:
                builder.setRegisterResponse(userInputBoundary.register(req.getRegister()));
                break;
            case RequestPacket.LOGIN_FIELD_NUMBER:
                builder.setLoginResponse(userInputBoundary.login(req.getLogin()));
                break;
            case RequestPacket.LOGOUT_FIELD_NUMBER:
                builder.setLogoutResponse(userInputBoundary.logout(req.getLogout()));
                break;
            case RequestPacket.CREATECHANNEL_FIELD_NUMBER:
                builder.setCreateChannelResponse(userInputBoundary.createChannel(req.getCreateChannel()));
                break;
            case RequestPacket.JOINCHANNEL_FIELD_NUMBER:
                builder.setJoinChannelResponse(channelInputBoundary.join(req.getJoinChannel()));
                break;
            case RequestPacket.LISTINGCHANNEL_FIELD_NUMBER:
                builder.setListingChannelResponse(userInputBoundary.listingChannel(req.getListingChannel()));
                break;
            case RequestPacket.SENDMESSAGETOCHANNEL_FIELD_NUMBER:
                builder.setNewMessageResponse(messageInputBoundary.send(req.getSendMessageToChannel()));
                break;
            case RequestPacket.READMESSAGE_FIELD_NUMBER:
                builder.setReadMessageResponse(messageInputBoundary.read(req.getReadMessage()));
                break;
            case RequestPacket.READMESSAGEOFCHANNEL_FIELD_NUMBER:
                builder.setReadMessageOfChannel(channelInputBoundary.readMessage(req.getReadMessageOfChannel()));
                break;
            default:
                builder.setUnknownResponse(ResponsePacket.Unknown.newBuilder()
                        .setMessage("Unknown request")
                        .build());
        }
        state.put(PacketResolveState.RESPONSE_KEY, builder.build());
    }

}
