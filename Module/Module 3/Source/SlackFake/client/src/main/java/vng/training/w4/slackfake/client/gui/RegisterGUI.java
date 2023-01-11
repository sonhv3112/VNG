package vng.training.w4.slackfake.client.gui;

import lombok.extern.log4j.Log4j2;
import vng.training.w4.slackfake.client.ChatClient;
import vng.training.w4.slackfake.protobuf.RequestPacket;

import javax.swing.*;
import java.awt.*;

@Log4j2
public final class RegisterGUI {
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
        RegisterGUI.client = client;
        if (panel != null) {
            setVisible(true);
            return;
        }
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("REGISTER");
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

        notificationLabel = new JLabel("Register failed!");
        notificationLabel.setBounds(100, 103, 300, 20);
        notificationLabel.setVisible(false);
        panel.add(notificationLabel);

        button = new JButton("Register");
        button.setBounds(100, 123, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(e -> {
            username = usernameField.getText();
            password = passwordField.getText();

            try {
                client.send(RequestPacket.newBuilder()
                        .setRegister(RequestPacket.Register
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

        subButton = new JButton("Login");
        subButton.setBounds(195, 123, 90, 25);
        subButton.setForeground(Color.WHITE);
        subButton.setBackground(Color.BLACK);
        subButton.addActionListener(e -> {
            try {
                RegisterGUI.setVisible(false);
                LoginGUI.start(client);
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

    public static void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
