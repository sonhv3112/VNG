package vng.training.w4.slackfake.client.model;

public interface User {

    String getUserId();

    String getUsername();

    String getPassword();

    String getSalt();

    String getAccessToken();

    String getName();

    void setUsername(String username);

    void setPassword(String password);

    void setSalt(String salt);

    void setAccessToken(String accessToken);

    void setName(String name);

}
