package vng.training.w4.slackfake.service.message;

import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.model.Channel;
import vng.training.w4.slackfake.model.CommonMessage;
import vng.training.w4.slackfake.model.Message;
import vng.training.w4.slackfake.repository.ChannelRepository;
import vng.training.w4.slackfake.repository.MessageRepository;
import vng.training.w4.slackfake.repository.UserRepository;
import vng.training.w4.slackfake.service.UserConnectionService;

import java.util.UUID;

@Service
public class AppMessageService implements MessageService {

    private UserRepository userRepository;
    private ChannelRepository channelRepository;
    private MessageRepository messageRepository;
    private final UserConnectionService userConnectionService;

    public AppMessageService(UserRepository userRepository, MessageRepository messageRepository, ChannelRepository channelRepository, UserConnectionService userConnectionService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userConnectionService = userConnectionService;
    }

    @Override
    public int sendMessage(String userId, String channelId, String content, String type) {
        if (!userRepository.containsUserId(userId))
            return 2;
        Channel channel = channelRepository.findById(channelId);
        if (channel == null)
            return 2;
        Message currentMessage = new CommonMessage(UUID.randomUUID().toString(), channelId, userId, content);
        channel.addMessage(currentMessage.getMessageId());
        channelRepository.save(channel);
        messageRepository.save(currentMessage);
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
