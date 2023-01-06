package vng.training.w4.slackfake.model;

public interface MessageFactory {

    Message createMessage(String messageId, String channelId, String userId, String content);

}
