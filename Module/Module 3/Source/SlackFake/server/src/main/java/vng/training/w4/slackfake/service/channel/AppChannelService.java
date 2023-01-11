package vng.training.w4.slackfake.service.channel;

import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.model.Channel;
import vng.training.w4.slackfake.model.ChannelFactory;
import vng.training.w4.slackfake.repository.ChannelRepository;
import vng.training.w4.slackfake.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AppChannelService implements ChannelService {

    private UserRepository userRepository;
    private ChannelFactory channelFactory;
    private ChannelRepository channelRepository;

    public AppChannelService(UserRepository userRepository, ChannelFactory channelFactory, ChannelRepository channelRepository) {
        this.userRepository = userRepository;
        this.channelFactory = channelFactory;
        this.channelRepository = channelRepository;
    }

    @Override
    public List<Channel> getChannelsContainUsername(String username) {
        System.out.println(username);
        List<Channel> result = new ArrayList<>();
        List<Channel> channels = channelRepository.getAll();
        for (Channel channel : channels) {
            if (channel.getMemberIds().contains(username)) {
                result.add(channel);
            }
        }
        return result;
    }

    @Override
    public Channel getChannelById(String id) {
        return channelRepository.findById(id);
    }

    @Override
    public Channel createChannel(String title, Set<String> memberIds) {
        Channel channel = channelFactory.createChannel(title, memberIds, null);
        channelRepository.save(channel);
        return channel;
    }

    @Override
    public void addMember(String channelId, String userId) {

    }

    @Override
    public void removeMember(String channelId, String userId) {

    }

    @Override
    public void deleteChannel(String channelId) {

    }

    @Override
    public void renameChannel(String channelId, String newTitle) {

    }

    @Override
    public void addMessage(String channelId, String messageId) {

    }

    @Override
    public void removeMessage(String channelId, String messageId) {

    }

    @Override
    public int join(String channelId, String userId) {
        if (!userRepository.containsUserId(userId))
            return 2;
        Channel channel = channelRepository.findById(channelId);
        if (channel == null)
            return 1;
        channel.addMember(userId);
        channelRepository.save(channel);
        return 0;
    }

    @Override
    public List<String> readAllMessage(String channelId) {
        Channel channel = channelRepository.findById(channelId);
        if (channel == null)
            return null;
        return channel.getMessageIds();
    }

    @Override
    public List<String> readMessage(String userId, String channelId, int indexStart, int indexEnd) {
        if (!userRepository.containsUserId(userId))
            return null;
        Channel channel = channelRepository.findById(channelId);
        if (channel == null || indexStart > indexEnd)
            return null;
        List<String> messageIds = new ArrayList<>();
        List<String> allMessageIds = channel.getMessageIds();
        if (allMessageIds == null)
            return messageIds;
        for (int i = Math.max(0, allMessageIds.size() - indexEnd); i <= allMessageIds.size() - indexStart; ++i) {
            messageIds.add(allMessageIds.get(i));
        }
        return messageIds;
    }
}
