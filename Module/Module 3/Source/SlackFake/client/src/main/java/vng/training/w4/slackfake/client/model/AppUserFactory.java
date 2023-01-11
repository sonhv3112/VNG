package vng.training.w4.slackfake.client.model;

import org.springframework.stereotype.Component;

@Component
public class AppUserFactory implements UserFactory {

    @Override
    public User createUser(String username, String password, String salt, String name) {
        return new CommonUser(username, password, salt, name);
    }

}
