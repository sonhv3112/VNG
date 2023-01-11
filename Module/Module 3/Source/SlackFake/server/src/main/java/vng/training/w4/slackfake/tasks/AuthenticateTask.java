package vng.training.w4.slackfake.tasks;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.service.security.AuthenticateResult;
import vng.training.w4.slackfake.service.security.SecurityService;

@Component
public class AuthenticateTask extends PacketResolveTask {

    private final SecurityService securityService;

    public AuthenticateTask(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doAccept(PacketResolveState state) {
        RequestPacket packet = state.get(PacketResolveState.REQUEST_KEY);
        AuthenticateResult result = securityService.authenticate(packet);
        ResponsePacket.Builder builder = (ResponsePacket.Builder) state.asMap().computeIfAbsent(PacketResolveState.RESPONSE_BUILDER_KEY, k -> ResponsePacket.newBuilder());
        if (result.isAuthenticated()) {
            state.put(PacketResolveState.USER_ID_KEY, result.getUserId());
            builder.setIsAuthorized(true);
        } else {
            builder.setIsAuthorized(false);
            state.stop();
        }
    }

}
