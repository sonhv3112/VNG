package vng.training.w4.slackfake.service.user;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface UserInputBoundary {

    ResponsePacket.Register register(RequestPacket.Register request);

    ResponsePacket.Logout logout(RequestPacket.Logout request);

    ResponsePacket.CreateChannel createChannel(RequestPacket.CreateChannel request);

    ResponsePacket.ListingChannel listingChannel(RequestPacket.ListingChannel request);

    ResponsePacket.Login login(RequestPacket.Login request);
}
