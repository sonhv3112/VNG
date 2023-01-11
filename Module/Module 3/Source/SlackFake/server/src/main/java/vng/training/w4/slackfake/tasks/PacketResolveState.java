package vng.training.w4.slackfake.tasks;

import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;

import java.util.HashMap;
import java.util.Map;

public class PacketResolveState {

    public static final String REQUEST_KEY = "request";
    public static final String RESPONSE_BUILDER_KEY = "response_builder";
    public static final String RESPONSE_KEY = "response";
    public static final String USER_ID_KEY = "userId";

    private final Map<String, Object> state = new HashMap<>();
    private boolean shouldContinue = true;

    public PacketResolveState(RequestPacket packet) {
        state.put(REQUEST_KEY, packet);
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }

    public void stop() {
        shouldContinue = false;
    }

    public Map<String, Object> asMap() {
        return state;
    }

    public void put(String key, Object value) {
        state.put(key, value);
    }

    public boolean containsKey(String key) {
        return state.containsKey(key);
    }

    public <V> V get(String key) {
        return (V) state.get(key);
    }

    public <V> V get(String key, V defaultValue) {
        V value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public void clear() {
        state.clear();
    }

}
