package vng.training.w4.slackfake.service.channel;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.Channel;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.utils.StringUtils;

import java.util.List;

@Component
public class ChannelInteractor implements ChannelInputBoundary {
    ChannelService channelService;

    public ChannelInteractor(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public ResponsePacket.JoinChannel join(RequestPacket.JoinChannel request) {
        String channelId = request.getChannelId();
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());

        int result = channelService.join(channelId, userId);

        if (result == 2)
            return ResponsePacket.JoinChannel
                    .newBuilder()
                    .setStatus(ResponsePacket.JoinChannel.Status.UNRECOGNIZED)
                    .setMessage("Join channel failed")
                    .build();
        if (result == 1)
            return ResponsePacket.JoinChannel
                    .newBuilder()
                    .setStatus(ResponsePacket.JoinChannel.Status.INVALID_CHANNEL_ID)
                    .setMessage("Join channel failed")
                    .build();
        return ResponsePacket.JoinChannel
                .newBuilder()
                .setStatus(ResponsePacket.JoinChannel.Status.SUCCESS)
                .setMessage("Join channel successful")
                .build();
    }

    @Override
    public ResponsePacket.ReadMessageOfChannel readMessage(RequestPacket.ReadMessageOfChannel request) {
        String channelId = request.getChannelId();
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());

        List<String> messageIds = channelService.readMessage(userId, channelId, request.getIndexStart(), request.getIndexEnd());
        ResponsePacket.ReadMessageOfChannel.Builder builder =
                ResponsePacket.ReadMessageOfChannel
                .newBuilder()
                .setStatusCode(200)
                .setStatus("Read successful");

        for (String messageId : messageIds)
            builder.addMessageIds(messageId);

        return builder.build();
    }
}
