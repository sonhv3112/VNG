package vng.training.w4.slackfake.client.model;

import org.springframework.stereotype.Component;

@Component
public class AppMessageFactory implements MessageFactory {

    @Override
    public Message createMessage(String messageId, String channelId, String userId, String content) {
        return new CommonMessage(messageId, channelId, userId, content);
    }

}
