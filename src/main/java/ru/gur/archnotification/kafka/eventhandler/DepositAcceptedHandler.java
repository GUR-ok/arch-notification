package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.DepositAcceptedEventData;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositAcceptedHandler implements EventHandler<DepositAcceptedEventData> {

//    private final service;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.DEPOSIT_ACCEPTED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final DepositAcceptedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

//        service.call
        System.out.printf("!!! DEPOSIT %s ACCEPTED ON ACCOUNT %s\n", eventSource.getValue(),
                eventSource.getAccountId());

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
