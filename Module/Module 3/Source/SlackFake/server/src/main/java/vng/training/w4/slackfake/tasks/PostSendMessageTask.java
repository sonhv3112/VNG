package vng.training.w4.slackfake.tasks;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.UserConnectionService;

@Component
@AllArgsConstructor
public class PostSendMessageTask extends PacketResolveTask {

    private final UserConnectionService userConnectionService;

    @Override
    protected void doAccept(PacketResolveState state) {
        ResponsePacket packet = state.get(PacketResolveState.RESPONSE_KEY);
        if (packet == null) {
            return;
        }

        if (!packet.hasNewMessageResponse()) {
            return;
        }

        ResponsePacket.NewMessage data = packet.getNewMessageResponse();
        if (data.getStatus() != ResponsePacket.NewMessage.Status.SUCCESS) {
            return;
        }

        userConnectionService.broadcast(packet);
        state.put(PacketResolveState.RESPONSE_KEY, null);
    }
}
