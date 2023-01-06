package vng.training.w4.slackfake.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.ChannelInputBoundary;
import vng.training.w4.slackfake.service.MessageInputBoundary;
import vng.training.w4.slackfake.service.UserInputBoundary;

@Component
public class AppPacketResolver implements PacketResolver {

    private final UserInputBoundary userInputBoundary;
    private final ChannelInputBoundary channelInputBoundary;
    private final MessageInputBoundary messageInputBoundary;

    public AppPacketResolver(UserInputBoundary userInputBoundary, ChannelInputBoundary channelInputBoundary, MessageInputBoundary messageInputBoundary) {
        this.userInputBoundary = userInputBoundary;
        this.channelInputBoundary = channelInputBoundary;
        this.messageInputBoundary = messageInputBoundary;
    }

    @Override
    public ResponsePacket resolve(RequestPacket packet) {
        ResponsePacket.Builder builder = ResponsePacket.newBuilder();
        RequestPacket.DataCase dataCase = packet.getDataCase();
        switch (dataCase.getNumber()) {
            case RequestPacket.REGISTER_FIELD_NUMBER:
                builder.setRegisterResponse(userInputBoundary.register(packet.getRegister()));
                break;
            case RequestPacket.LOGIN_FIELD_NUMBER:
                builder.setLoginResponse(userInputBoundary.login(packet.getLogin()));
                break;
            case RequestPacket.LOGOUT_FIELD_NUMBER:
                builder.setLogoutResponse(userInputBoundary.logout(packet.getLogout()));
                break;
            case RequestPacket.CREATECHANNEL_FIELD_NUMBER:
                builder.setCreateChannelResponse(userInputBoundary.createChannel(packet.getCreateChannel()));
                break;
            case RequestPacket.JOINCHANNEL_FIELD_NUMBER:
                builder.setJoinChannelResponse(channelInputBoundary.join(packet.getJoinChannel()));
                break;
            case RequestPacket.LISTINGCHANNEL_FIELD_NUMBER:
                builder.setListingChannelResponse(userInputBoundary.listingChannel(packet.getListingChannel()));
                break;
            case RequestPacket.SENDMESSAGETOCHANNEL_FIELD_NUMBER:
                builder.setSendMessageToChannelResponse(messageInputBoundary.send(packet.getSendMessageToChannel()));
                break;
            case RequestPacket.READMESSAGE_FIELD_NUMBER:
                builder.setReadMessageResponse(messageInputBoundary.read(packet.getReadMessage()));
                break;
            default:
                builder.setUnknownResponse(ResponsePacket.Unknown.newBuilder()
                        .setMessage("Unknown request")
                        .build());
        }
        return builder.build();
    }

}
