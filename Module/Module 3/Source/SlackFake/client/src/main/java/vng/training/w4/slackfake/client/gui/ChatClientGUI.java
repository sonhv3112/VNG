package vng.training.w4.slackfake.client.gui;

import vng.training.w4.slackfake.client.ChatClient;
import vng.training.w4.slackfake.client.model.Channel;
import vng.training.w4.slackfake.client.model.CommonMessage;
import vng.training.w4.slackfake.client.model.Message;
import vng.training.w4.slackfake.client.model.User;
import vng.training.w4.slackfake.protobuf.RequestPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.*;


public final class ChatClientGUI {
    private static JPanel panel;
    private static JFrame frame;

    private static JLabel accountInfo;
    private static JTextArea chatBox;
    public static JPanel channelsPanel;
    public static JScrollPane channelsScrollPane;
    private static JScrollPane chatBoxPane;
    private static JTextArea textBox;
    private static JButton enterButton;
    private static User user;
    private static Channel focusChannel;

    private static Map<String, List<Message>> messagesOfChannels;
    private static List<Channel> channels;
    private static List<JButton> buttonChannels;
    private static ChatClient client;
    private static JButton joinChannelButton, createChannelButton;
    private static BoxLayout channelsLayout;

    public static void start(User user, ChatClient client) throws IOException {
        ChatClientGUI.client = client;
        ChatClientGUI.user = user;
        messagesOfChannels = new HashMap<>();
        buttonChannels = new ArrayList<>();

        initGUI();
        requestListChannels();
    }

    private static void initGUI() {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("Chat GUI");
        frame.setLocation(new Point(100, 100));
        frame.add(panel);
        frame.setSize(610, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accountInfo = new JLabel("Hello: " + user.getName(), SwingConstants.CENTER);
        accountInfo.setBounds(0, 0, 200, 50);
        panel.add(accountInfo);

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);

        chatBoxPane = new JScrollPane(chatBox);
        chatBoxPane.setBounds(200, 0, 400, 400);
        panel.add(chatBoxPane);

        joinChannelButton = new JButton("Join Channel");
        joinChannelButton.setBounds(0, 50, 200, 50);
        joinChannelButton.addActionListener(e -> {
            showJoinChannel();
        });
        panel.add(joinChannelButton);

        createChannelButton = new JButton("Create Channel");
        createChannelButton.setBounds(0, 100, 200, 50);
        createChannelButton.addActionListener(e -> {
            showCreateChannel();
        });
        panel.add(createChannelButton);

        ChatClientGUI.channelsPanel = new JPanel();
        channelsLayout = new BoxLayout(ChatClientGUI.channelsPanel, BoxLayout.Y_AXIS);
        ChatClientGUI.channelsPanel.setLayout(channelsLayout);
        channelsPanel.setBounds(0, 150, 200, 300);

        ChatClientGUI.channelsScrollPane = new JScrollPane(ChatClientGUI.channelsPanel);
        ChatClientGUI.channelsScrollPane.setBounds(0, 150, 200, 350);
//        channelsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        channelsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        ChatClientGUI.panel.add(ChatClientGUI.channelsScrollPane);


        textBox = new JTextArea();
        textBox.setBounds(200, 400, 300, 100);
        textBox.setLineWrap(true);
        textBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panel.add(textBox);

        enterButton = new JButton("Enter");
        enterButton.setBounds(500 , 400, 100, 100);
        enterButton.setHorizontalAlignment(SwingConstants.CENTER);
        enterButton.setContentAreaFilled(false);
        enterButton.setBorderPainted(false);
        enterButton.setFocusPainted(false);
        enterButton.addActionListener(e -> {
            String content = textBox.getText();
            if (!content.strip().equals("") && focusChannel != null) {
                Message message = new CommonMessage(UUID.randomUUID().toString(),
                        ChatClientGUI.focusChannel.getChannelId(),
                        ChatClientGUI.user.getUserId(),
                        content);
                try {
                    sendMessage(message);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                textBox.setText("");
                textBox.requestFocus();
            }
        });
        panel.add(enterButton);

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to logout?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        client.send(RequestPacket.newBuilder()
                                .setLogout(RequestPacket.Logout.newBuilder()
                                        .setAccessToken(user.getAccessToken())
                                        .build())
                                .build(), callback -> {

                        });
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.exit(0);
                }
            }
        });
    }

    private static void showCreateChannel() {
        String channelName = JOptionPane.showInputDialog(frame, "Enter channel name ");
        if (channelName != null && !channelName.strip().equals("")) {
            try {
                client.send(RequestPacket.newBuilder()
                        .setCreateChannel(RequestPacket.CreateChannel.newBuilder()
                                .setAccessToken(user.getAccessToken())
                                .setChannelName(channelName)
                                .build())
                        .build(), callback -> {

                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void showJoinChannel() {
        String channelId = JOptionPane.showInputDialog(frame, "Enter channel id ");
        if (channelId != null && !channelId.strip().equals("")) {
            try {
                client.send(RequestPacket.newBuilder()
                        .setJoinChannel(RequestPacket.JoinChannel.newBuilder()
                                .setAccessToken(user.getAccessToken())
                                .setChannelId(channelId)
                                .build())
                        .build(), callback -> {

                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void requestListChannels() throws IOException {
        client.send(RequestPacket.newBuilder()
                .setListingChannel(RequestPacket.ListingChannel.newBuilder()
                        .setAccessToken(user.getAccessToken())
                        .build())
                .build(), callback -> {

        });
    }

    public static void close() {
        frame.dispose();
    }

    private static void createButtonChannel(Channel channel) {
        if (channel.getTitle() == null || channel.getChannelId() == null)
            return;
        ChatClientGUI.buttonChannels.add(new JButton(String.format("%s (%s)", channel.getTitle(), channel.getChannelId())));
        JButton button = ChatClientGUI.buttonChannels.get(ChatClientGUI.buttonChannels.size() - 1);
        button.setSize(90, 100);
        button.setPreferredSize(new Dimension(90, 100));
//        button.setLayout(ChatClientGUI.channelsLayout);
//        button.setPreferredSize(new Dimension(90, 50));
        button.addActionListener(e -> {
            ChatClientGUI.focusChannel = channel;
            try {
                updateChatBox();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ChatClientGUI.channelsPanel.add(button);
    }

    public static void setChannels(List<Channel> channels) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> setChannels(channels));
            return;
        }
        ChatClientGUI.channels = channels;
        ChatClientGUI.channelsPanel.removeAll();
        ChatClientGUI.buttonChannels.clear();
        for (Channel channel : channels) {
            createButtonChannel(channel);
        }
    }

    public static void addChannel(Channel channel) {
        channels.add(channel);
        createButtonChannel(channel);
    }

    private static void updateChatBox() throws IOException {
        if (focusChannel == null)
            return;
        if (!messagesOfChannels.containsKey(focusChannel.getChannelId())) {
            messagesOfChannels.put(focusChannel.getChannelId(), new ArrayList<>());
            client.send(RequestPacket.newBuilder()
                    .setReadMessageOfChannel(RequestPacket.ReadMessageOfChannel
                            .newBuilder()
                            .setAccessToken(user.getAccessToken())
                            .setChannelId(focusChannel.getChannelId())
                            .setIndexStart(1)
                            .setIndexEnd(10000)
                            .build())
                    .build(), callback -> {

            });
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Channel: " + focusChannel.getTitle() + '\n');
            stringBuilder.append("Channel ID: " + focusChannel.getChannelId() + '\n');
            stringBuilder.append("-----------------------------------------------------\n");
            for (Message message : messagesOfChannels.get(focusChannel.getChannelId())) {
                if (user.getUserId().equals(message.getSenderId())) {
                    stringBuilder
                            .append("You:\n")
                            .append(message.getContent() + "\n\n");
                } else {
                    stringBuilder
                            .append(message.getSenderId() + ":\n")
                            .append(message.getContent() + "\n\n");
                }
            }
            chatBox.setText(stringBuilder.toString());
        }
    }

    public static void addMessageIdsToChannels(List<String> messageIds) throws IOException {
        for (String messageId : messageIds) {
            client.send(RequestPacket.newBuilder()
                    .setReadMessage(RequestPacket.ReadMessage
                            .newBuilder()
                            .setAccessToken(user.getAccessToken())
                            .setMessageId(messageId)
                            .build())
                    .build(), callback -> {

            });
        }
    }

    public static void addMessagesToChannels(Message message) throws IOException {
        String channelId = message.getChannelId();
        if (channelId == null)
            return;
        if (!messagesOfChannels.containsKey(channelId))
            messagesOfChannels.put(channelId, new ArrayList<>());
        messagesOfChannels.get(channelId).add(message);
        updateChatBox();
    }

    public static void sendMessage(Message message) throws IOException {
        if (focusChannel == null)
            return;
        client.send(RequestPacket.newBuilder()
                .setSendMessageToChannel(RequestPacket.SendMessageToChannel.newBuilder()
                        .setAccessToken(user.getAccessToken())
                        .setChannelId(focusChannel.getChannelId())
                        .setContent(message.getContent())
                        .build())
                .build(), callback -> {

        });

//        if (!messagesOfChannels.containsKey(focusChannel.getChannelId()))
//            messagesOfChannels.put(focusChannel.getChannelId(), new ArrayList<>());
//        messagesOfChannels.get(focusChannel.getChannelId()).add(message);
    }

}
