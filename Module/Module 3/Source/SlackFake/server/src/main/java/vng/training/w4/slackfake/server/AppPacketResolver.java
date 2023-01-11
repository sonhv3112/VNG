package vng.training.w4.slackfake.server;

import com.google.common.util.concurrent.Futures;
import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.tasks.*;

import java.util.concurrent.Future;

@Component
@Log4j2
public class AppPacketResolver implements PacketResolver {

    private final PublishSubject<PacketResolveState> resolver = PublishSubject.create();

    public AppPacketResolver(PacketIdSetterTask packetIdSetterTask, AuthenticateTask authenticateTask, ResponseResolverTask responseResolverTask, PostSendMessageTask postSendMessageTask) {
        resolver
                .doOnNext(packetIdSetterTask::accept)
                .doOnNext(authenticateTask::accept)
                .doOnNext(responseResolverTask::accept)
                .doOnNext(postSendMessageTask::accept)
                .doOnError(e -> {
                    log.error("Error while resolving packet", e);
                })
                .subscribe();
    }

    @Override
    public Future<Void> resolve(PacketResolveState state) {
        resolver.onNext(state);
        return Futures.immediateVoidFuture();
    }

}
