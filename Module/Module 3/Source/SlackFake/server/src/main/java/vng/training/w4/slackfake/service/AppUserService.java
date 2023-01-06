package vng.training.w4.slackfake.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.model.User;
import vng.training.w4.slackfake.model.UserFactory;
import vng.training.w4.slackfake.repository.UserRepository;
import vng.training.w4.slackfake.utils.PasswordUtils;
import vng.training.w4.slackfake.utils.StringUtils;

import static vng.training.w4.slackfake.utils.PasswordUtils.*;

@Service
public class AppUserService implements UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public AppUserService(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password, String name) {
        String salt = generateSalt();
        String hashedPassword = hashing(password, salt);

        userRepository.save(userFactory.createUser(username, hashedPassword, salt, name));
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        System.out.println(username + password);
        if (user != null)
            System.out.println(user.getPassword());

        if (user != null
                && (user.getAccessToken() == null || user.getAccessToken().equals(""))
                && PasswordUtils.passwordCompare(user.getPassword(), password, user.getSalt())) {
            String jwt = StringUtils.generateAccessTokenWithUserId(user.getUserId());
            user.setAccessToken(jwt);
            userRepository.save(user);
            return jwt;
        }
        return null;
    }

    @Override
    public void logout(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setAccessToken(null);
        userRepository.update(user);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordCompare(user.getPassword(), oldPassword, user.getSalt())) {
            throw new RuntimeException("Wrong password");
        }

        newPassword = hashing(newPassword, user.getSalt());
        user.setPassword(newPassword);
        userRepository.update(user);
    }

    @Override
    public void changeName(String username, String newName) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setName(newName);
        userRepository.update(user);
    }

    @Override
    public boolean isUsernameExisted(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByAccessToken(String accessToken) {
        String username = ""; //TODO resolve from accessToken

        return userRepository.findByUsername(username);
    }


}
