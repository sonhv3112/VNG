package vng.training.w4.slackfake.service;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.Message;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.utils.StringUtils;

@Component
public class MessageInteractor implements MessageInputBoundary {
    MessageService messageService;

    @Override
    public ResponsePacket.SendMessageToChannel send(RequestPacket.SendMessageToChannel request) {
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());
        String channelId = request.getChannelId();
        String content = request.getContent();
        String type = request.getType();

        int result = messageService.sendMessage(userId, channelId, content, type);

        if (result == 0)
            return ResponsePacket.SendMessageToChannel.newBuilder()
                    .setStatusCode(200)
                    .setStatus("Send message to channel successful")
                    .build();
        if (result == 2)
            return ResponsePacket.SendMessageToChannel
                    .newBuilder()
                    .setStatusCode(401)
                    .setStatus("Unauthorized")
                    .build();
        return ResponsePacket.SendMessageToChannel.newBuilder()
                .setStatusCode(403)
                .setStatus("Error")
                .build();
    }

    @Override
    public ResponsePacket.ReadMessage read(RequestPacket.ReadMessage request) {
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());
        String messageId = request.getMessageId();

        Message result = messageService.readMessage(userId, messageId);
        if (result == null)
            return ResponsePacket.ReadMessage.newBuilder()
                    .setStatusCode(403)
                    .setStatus("Error")
                    .build();

        return ResponsePacket.ReadMessage.newBuilder()
                .setStatusCode(200)
                .setStatus("Success")
                .setContent(result.getContent())
                .setDate(result.getDate().toString())
                .setType(result.getType())
                .setSenderId(result.getSenderId())
                .build();
    }
}
