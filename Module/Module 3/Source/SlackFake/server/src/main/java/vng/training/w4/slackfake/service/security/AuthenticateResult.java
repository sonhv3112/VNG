package vng.training.w4.slackfake.service.security;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import vng.training.w4.slackfake.service.ResponseResolveResult;

@AllArgsConstructor
public class AuthenticateResult {

    public static final AuthenticateResult SUCCESS = new AuthenticateResult(true, null, null);

    private final boolean authenticated;
    private final String userId;
    private final ResponseResolveResult unauthenticatedResponse;

    public boolean isAuthenticated() {
        return authenticated;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    @Nullable
    public ResponseResolveResult getUnauthenticatedResponse() {
        return unauthenticatedResponse;
    }

}
