package vng.training.w4.slackfake.repository;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RocksDBRepository {


    @Component
    public static class RocksDBUser implements UserRepository {
        private final AppRocksDB rocksDB;

        public RocksDBUser() {
            rocksDB = new AppRocksDB("user");

            // logout all account
            List<Object> userList = rocksDB.getAll();
            for (Object object : userList) {
                User user = (User) object;
                if (user.getAccessToken() == null)
                    continue;
                user.setAccessToken(null);
                this.update(user);
            }
        }

        @Override
        public User findByUsername(String username) {
            if (username == null)
                return null;
            return (User) rocksDB.find(username);
        }

        @Override
        public void save(User user) {
            rocksDB.save(user.getUsername(), user);
        }

        @Override
        public void update(User user) {
            rocksDB.save(user.getUsername(), user);
        }

        @Override
        public void delete(User user) {
            rocksDB.delete(user.getUsername());
        }

        @Override
        public boolean containsUserId(String userId) {
            User user = (User) rocksDB.find(userId);
            return user != null;
        }
    }

    @Component
    public static class RocksDBChannel implements ChannelRepository {
        private final AppRocksDB rocksDB;

        public RocksDBChannel() {
            this.rocksDB = new AppRocksDB("channel");
        }

        @Override
        public void save(Channel channel) {
            rocksDB.save(channel.getChannelId(), channel);
        }

        @Override
        public Channel findById(String channelId) {
            if (channelId == null)
                return null;
            return (Channel) rocksDB.find(channelId);
        }

        @Override
        public List<Channel> getAll() {
            List<Object> objectList = rocksDB.getAll();
            List<Channel> channelList = new ArrayList<>();
            for (Object object : objectList)
                channelList.add((Channel) object);
            return channelList;
        }

        @Override
        public void deleteById(String channelId) {
            rocksDB.delete(channelId);
        }

    }

    @Component
    public static class RocksDBMessage implements MessageRepository {
        private final AppRocksDB rocksDB;

        public RocksDBMessage() {
            this.rocksDB = new AppRocksDB("message");
        }

        @Override
        public void save(Message message) {
            rocksDB.save(message.getMessageId(), message);
        }

        @Override
        public Message findById(String messageId) {
            if (messageId == null)
                return null;
            return (Message) rocksDB.find(messageId);
        }

        @Override
        public void deleteById(String messageId) {
            rocksDB.delete(messageId);
        }
    }
}
