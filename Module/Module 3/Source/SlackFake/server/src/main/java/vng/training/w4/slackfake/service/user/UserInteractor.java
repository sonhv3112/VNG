package vng.training.w4.slackfake.service.user;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.model.Channel;
import vng.training.w4.slackfake.model.User;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.channel.ChannelService;

import java.util.List;
import java.util.Set;

@Component
public class UserInteractor implements UserInputBoundary {

    private final UserService userService;
    private final ChannelService channelService;

    public UserInteractor(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @Override
    public ResponsePacket.Register register(RequestPacket.Register data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String name = data.getName();

        if (userService.isUsernameExisted(username)) {
            return ResponsePacket.Register.newBuilder()
                    .setStatus(ResponsePacket.Register.Status.USERNAME_EXISTED)
                    .setMessage("Username is existed")
                    .build();
        }

        userService.register(username, password, name);

        return ResponsePacket.Register.newBuilder()
                .setStatus(ResponsePacket.Register.Status.SUCCESS)
                .setMessage("Register successfully")
                .build();
    }

    @Override
    public ResponsePacket.Logout logout(RequestPacket.Logout request) {
        String accessToken = request.getAccessToken();
        User user = userService.getUserByAccessToken(accessToken);

        if (user == null) {
            return ResponsePacket.Logout.newBuilder()
                    .setStatus(ResponsePacket.Logout.Status.INVALID_ACCESS_TOKEN)
                    .build();
        }

        userService.logout(user.getUsername());

        return ResponsePacket.Logout.newBuilder()
                .setStatus(ResponsePacket.Logout.Status.SUCCESS)
                .setMessage("Logout successfully")
                .build();
    }

    @Override
    public ResponsePacket.CreateChannel createChannel(RequestPacket.CreateChannel request) {
        String accessToken = request.getAccessToken();
        String channelName = request.getChannelName();

        User user = userService.getUserByAccessToken(accessToken);

        Channel channel = channelService.createChannel(channelName, Sets.newHashSet(user.getUserId()));

        return ResponsePacket.CreateChannel.newBuilder()
                .setStatus(ResponsePacket.CreateChannel.Status.SUCCESS)
                .setMessage("Create channel successfully")
                .setChannelId(channel.getChannelId())
                .build();
    }

    @Override
    public ResponsePacket.ListingChannel listingChannel(RequestPacket.ListingChannel request) {
        String accessToken = request.getAccessToken();

        User user = userService.getUserByAccessToken(accessToken);

        List<Channel> channels = channelService.getChannelsContainUsername(user.getUsername());

        ResponsePacket.ListingChannel.Builder builder = ResponsePacket.ListingChannel.newBuilder()
                .setStatus(ResponsePacket.ListingChannel.Status.SUCCESS)
                .setMessage("Listing channel successfully");

        for (Channel channel : channels)
            builder.addChannelIds(channel.getChannelId());
        for (Channel channel : channels)
            builder.addChannelNames(channel.getTitle());

        return builder.build();
    }

    @Override
    public ResponsePacket.Login login(RequestPacket.Login request) {
        String username = request.getUsername();
        String password = request.getPassword();

        String jwt = userService.login(username, password);

        if (jwt == null)
            return ResponsePacket.Login.newBuilder()
                    .setStatus(ResponsePacket.Login.Status.INVALID_USERNAME)
                    .setMessage("Login failed")
                    .build();

        return ResponsePacket.Login.newBuilder()
                .setStatus(ResponsePacket.Login.Status.SUCCESS)
                .setMessage("Login successfully")
                .setAccessToken(jwt)
                .build();
    }

}
