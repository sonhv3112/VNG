package vng.training.w4.slackfake.service;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.utils.StringUtils;

@Component
public class ChannelInteractor implements ChannelInputBoundary {
    ChannelService channelService;

    @Override
    public ResponsePacket.JoinChannel join(RequestPacket.JoinChannel request) {
        String channelId = request.getChannelId();
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());

        int result = channelService.join(channelId, userId);

        if (result == 2)
            return ResponsePacket.JoinChannel
                    .newBuilder()
                    .setStatusCode(401)
                    .setStatus("Unauthorized")
                    .build();
        if (result == 1)
            return ResponsePacket.JoinChannel
                    .newBuilder()
                    .setStatusCode(403)
                    .setStatus("Invalid channel id")
                    .build();
        return ResponsePacket.JoinChannel
                .newBuilder()
                .setStatusCode(200)
                .setStatus("Join channel successful")
                .build();
    }
}
