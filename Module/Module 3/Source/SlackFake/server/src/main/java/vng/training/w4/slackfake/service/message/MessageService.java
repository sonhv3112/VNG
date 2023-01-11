package vng.training.w4.slackfake.service.message;

import vng.training.w4.slackfake.model.Message;

public interface MessageService {
    int sendMessage(String userId, String channelId, String content, String type);
    Message readMessage(String userId, String messageId);
}
