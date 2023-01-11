package vng.training.w4.slackfake.client.gui;

import lombok.extern.log4j.Log4j2;
import vng.training.w4.slackfake.client.ChatClient;
import vng.training.w4.slackfake.client.model.CommonUser;
import vng.training.w4.slackfake.client.model.User;
import vng.training.w4.slackfake.protobuf.RequestPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Log4j2
public final class LoginGUI {
    private static JLabel passwordLabel, usernameLabel, notificationLabel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JButton button;
    private static JButton subButton;
    private static JPanel panel;
    private static JFrame frame;
    private static ChatClient client;
    private static String username, password;

    public static void start(ChatClient client) throws Exception {
        LoginGUI.client = client;
        if (panel != null) {
            setVisible(true);
            return;
        }
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("LOGIN");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 8, 70, 20);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 27, 193, 28);
        panel.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 55, 70, 20);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 75, 193, 28);
        panel.add(passwordField);

        notificationLabel = new JLabel("Login failed!");
        notificationLabel.setBounds(100, 103, 300, 20);
        notificationLabel.setVisible(false);
        panel.add(notificationLabel);

        button = new JButton("Login");
        button.setBounds(100, 123, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(e -> {
            username = usernameField.getText();
            password = passwordField.getText();

            try {
                client.send(RequestPacket.newBuilder()
                        .setLogin(RequestPacket.Login
                                .newBuilder()
                                .setUsername(username)
                                .setPassword(password)
                                .build())
                        .build(), callback -> {

                });
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(button);

        subButton = new JButton("Register");
        subButton.setBounds(195, 123, 90, 25);
        subButton.setForeground(Color.WHITE);
        subButton.setBackground(Color.BLACK);
        subButton.addActionListener(e -> {
            try {
                LoginGUI.setVisible(false);
                RegisterGUI.start(client);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(subButton);

        frame.setVisible(true);
    }

    public static void loginFailed(String error) {
        notificationLabel.setText(error);
        notificationLabel.setVisible(true);
    }

    public static void loginSuccess(String accessToken) throws IOException {
        LoginGUI.setVisible(false);
        User user = new CommonUser(username, password, "", username);
        user.setAccessToken(accessToken);
        ChatClientGUI.start(user, client);
    }

    public static void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
