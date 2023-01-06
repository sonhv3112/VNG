package vng.training.w4.slackfake.repository;

import vng.training.w4.slackfake.model.User;

public interface UserRepository {

    User findByUsername(String username);

    void save(User user);

    void update(User user);

    void delete(User user);

    boolean containsUserId(String userId);

}
