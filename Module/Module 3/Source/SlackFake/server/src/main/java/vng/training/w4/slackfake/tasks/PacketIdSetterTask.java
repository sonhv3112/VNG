package vng.training.w4.slackfake.tasks;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

@Component
public class PacketIdSetterTask extends PacketResolveTask {

    @Override
    protected void doAccept(PacketResolveState state) {
        RequestPacket packet = state.get(PacketResolveState.REQUEST_KEY);
        ResponsePacket.Builder builder = (ResponsePacket.Builder) state.asMap().computeIfAbsent(PacketResolveState.RESPONSE_BUILDER_KEY, k -> ResponsePacket.newBuilder());
        builder.setPacketId(packet.getPacketId());
    }

}
