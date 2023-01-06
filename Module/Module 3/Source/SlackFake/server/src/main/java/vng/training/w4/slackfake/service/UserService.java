package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.model.User;

public interface UserService {

    void register(String username, String password, String name);

    String login(String username, String password);

    void logout(String username);

    void changePassword(String username, String oldPassword, String newPassword);

    void changeName(String username, String newName);

    boolean isUsernameExisted(String username);

    User getUserByUsername(String username);

    User getUserById(long id);

    User getUserByAccessToken(String accessToken);

}
