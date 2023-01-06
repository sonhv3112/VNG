package vng.training.w4.slackfake.model;

public interface UserFactory {

    User createUser(String username, String password, String salt, String name);

}
