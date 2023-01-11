package vng.training.w4.slackfake.tasks;

import java.util.function.Consumer;

public abstract class PacketResolveTask implements Consumer<PacketResolveState> {

    @Override
    public void accept(PacketResolveState state) {
        if (state.shouldContinue()) {
            doAccept(state);
        }
    }

    protected abstract void doAccept(PacketResolveState state);

}
