package vng.training.w4.slackfake.client.model;

import java.io.Serializable;

public class CommonUser implements User, Serializable {

    private String username;
    private String password;
    private String name;
    private String userId;
    private String accessToken;
    private String salt;

    public CommonUser(String username, String password, String salt, String name) {
        this.userId = username;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
