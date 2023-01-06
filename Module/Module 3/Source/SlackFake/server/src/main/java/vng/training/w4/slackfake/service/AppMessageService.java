package vng.training.w4.slackfake.service;

import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.model.CommonMessage;
import vng.training.w4.slackfake.model.Message;
import vng.training.w4.slackfake.repository.ChannelRepository;
import vng.training.w4.slackfake.repository.MessageRepository;
import vng.training.w4.slackfake.repository.UserRepository;
import vng.training.w4.slackfake.utils.StringUtils;

@Service
public class AppMessageService implements MessageService {

    private UserRepository userRepository;
    private ChannelRepository channelRepository;
    private MessageRepository messageRepository;

    public AppMessageService(UserRepository userRepository, MessageRepository messageRepository, ChannelRepository channelRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
    }

    @Override
    public int sendMessage(String userId, String channelId, String content, String type) {
        if (!userRepository.containsUserId(userId))
            return 2;
        messageRepository.save(new CommonMessage(StringUtils.generateRandomString(64), channelId, userId, content));
        return 0;
    }

    @Override
    public Message readMessage(String userId, String messageId) {
        if (!userRepository.containsUserId(userId))
            return null;
        Message message = messageRepository.findById(messageId);
        return message;
    }
}
