package vng.training.w4.slackfake.server;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.ChatUserConnection;
import vng.training.w4.slackfake.tasks.PacketResolveState;

import java.util.List;
import java.util.concurrent.Future;

public interface PacketResolver {

    Future<Void> resolve(PacketResolveState state);

}
