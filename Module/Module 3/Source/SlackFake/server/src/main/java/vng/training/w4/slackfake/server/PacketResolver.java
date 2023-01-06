package vng.training.w4.slackfake.server;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface PacketResolver {

    ResponsePacket resolve(RequestPacket packet);

}
