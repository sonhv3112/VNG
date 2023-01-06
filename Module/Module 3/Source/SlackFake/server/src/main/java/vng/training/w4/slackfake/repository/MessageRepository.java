package vng.training.w4.slackfake.repository;

import vng.training.w4.slackfake.model.Message;

public interface MessageRepository {

    void save(Message message);

    Message findById(String messageId);

    void deleteById(String messageId);

}
