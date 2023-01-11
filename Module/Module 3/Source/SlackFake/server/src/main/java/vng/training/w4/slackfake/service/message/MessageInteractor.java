package vng.training.w4.slackfake.service.message;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.Message;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.UserConnectionService;
import vng.training.w4.slackfake.utils.StringUtils;

@Component
public class MessageInteractor implements MessageInputBoundary {
    private final MessageService messageService;
    private final UserConnectionService userConnectionService;

    public MessageInteractor(MessageService messageService, UserConnectionService userConnectionService) {
        this.messageService = messageService;
        this.userConnectionService = userConnectionService;
    }

    @Override
    public ResponsePacket.NewMessage send(RequestPacket.SendMessageToChannel request) {
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());
        String channelId = request.getChannelId();
        String content = request.getContent();
        String type = request.getType();

        int result = messageService.sendMessage(userId, channelId, content, type);

//        if (result == 0)
//            return ResponsePacket.SendMessageToChannel.newBuilder()
//                    .setStatusCode(200)
//                    .setStatus("Send message to channel successful")
//                    .build();
//        if (result == 2)
//            return ResponsePacket.SendMessageToChannel
//                    .newBuilder()
//                    .setStatusCode(401)
//                    .setStatus("Unauthorized")
//                    .build();
//        return ResponsePacket.SendMessageToChannel.newBuilder()
//                .setStatusCode(403)
//                .setStatus("Error")
//                .build();
        if (result == 0) {
            return ResponsePacket.NewMessage.newBuilder()
                    .setMessage("Success")
                    .setStatus(ResponsePacket.NewMessage.Status.SUCCESS)
                    .setMessageId("0")
                    .setChannelId(channelId)
                    .setContent(content)
                    .setType(type)
                    .setSenderId(userId)
                    .build();
        }
        return ResponsePacket.NewMessage.newBuilder()
                .setMessage("Send message to channel failed")
                .setStatus(ResponsePacket.NewMessage.Status.UNRECOGNIZED)
                .build();
    }

    @Override
    public ResponsePacket.ReadMessage read(RequestPacket.ReadMessage request) {
        String userId = StringUtils.decodeUsernameFromAccessToken(request.getAccessToken());
        String messageId = request.getMessageId();

        Message result = messageService.readMessage(userId, messageId);
        if (result == null)
            return ResponsePacket.ReadMessage.newBuilder()
                    .setMessage("Read message failed")
                    .setStatus(ResponsePacket.ReadMessage.Status.UNRECOGNIZED)
                    .build();

        return ResponsePacket.ReadMessage.newBuilder()
                .setMessage("Read message successful")
                .setStatus(ResponsePacket.ReadMessage.Status.SUCCESS)
                .setMessageId(result.getMessageId())
                .setChannelId(result.getChannelId())
                .setContent(result.getContent())
                .setDate(result.getDate().toString())
                .setType(result.getType())
                .setSenderId(result.getSenderId())
                .build();
    }
}
