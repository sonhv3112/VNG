package vng.training.w4.slackfake.repository;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.*;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryRepository {

    @Component
    public static class InMemoryUser implements UserRepository {
        private final List<User> memoryUser;
        private final UserFactory userFactory;

        public InMemoryUser(UserFactory userFactory) {
            this.userFactory = userFactory;
            memoryUser = new ArrayList<>();
        }

        @Override
        public User findByUsername(String username) {
            for (User user : memoryUser) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null;
        }

        @Override
        public void save(User user) {
            memoryUser.add(user);
        }

        @Override
        public void update(User user) {
            for (int i = 0; i < memoryUser.size(); i++) {
                if (memoryUser.get(i).getUsername().equals(user.getUsername())) {
                    memoryUser.set(i, user);
                    return;
                }
            }
        }

        @Override
        public void delete(User user) {
            memoryUser.remove(user);
        }

        @Override
        public boolean containsUserId(String userId) {
            for (User user : memoryUser)
                if (user.getUsername().equals(userId))
                    return true;
            return false;
        }
    }

    @Component
    public static class InMemoryChannel implements ChannelRepository {
        private final List<Channel> memoryChannel;
        private final ChannelFactory channelFactory;

        public InMemoryChannel(ChannelFactory channelFactory) {
            this.channelFactory = channelFactory;
            memoryChannel = new ArrayList<>();
        }

        @Override
        public void save(Channel channel) {
            memoryChannel.add(channel);
        }

        @Override
        public Channel findById(String channelId) {
            for (Channel channel : memoryChannel) {
                if (channel.getChannelId().equals(channelId)) {
                    return channel;
                }
            }
            return null;
        }

        @Override
        public List<Channel> getAll() {
            return memoryChannel;
        }

        @Override
        public void deleteById(String channelId) {
            for (int i = 0; i < memoryChannel.size(); i++) {
                if (memoryChannel.get(i).getChannelId().equals(channelId)) {
                    memoryChannel.remove(i);
                    return;
                }
            }
        }

    }

    @Component
    public static class InMemoryMessage implements MessageRepository {
        private final List<Message> memoryMessage;

        public InMemoryMessage() {
            memoryMessage = new ArrayList<>();
        }

        @Override
        public void save(Message message) {
            memoryMessage.add(message);
        }

        @Override
        public Message findById(String messageId) {
            for (Message message : memoryMessage) {
                if (message.getMessageId().equals(messageId)) {
                    return message;
                }
            }
            return null;
        }

        @Override
        public void deleteById(String messageId) {
            for (int i = 0; i < memoryMessage.size(); i++) {
                if (memoryMessage.get(i).getMessageId().equals(messageId)) {
                    memoryMessage.remove(i);
                    return;
                }
            }
        }
    }

}
