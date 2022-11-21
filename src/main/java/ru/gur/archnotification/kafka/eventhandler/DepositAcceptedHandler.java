package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.DepositAcceptedEventData;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.service.MessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositAcceptedHandler implements EventHandler<DepositAcceptedEventData> {

    private final MessageService messageService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.DEPOSIT_ACCEPTED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final DepositAcceptedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        messageService.sendMessage(eventSource.getAccountId(),
                String.format("DEPOSIT %s ACCEPTED ON ACCOUNT %s",
                        eventSource.getValue(), eventSource.getAccountId()),
                eventSource.getEvent());

        System.out.printf("!!! DEPOSIT %s ACCEPTED ON ACCOUNT %s\n", eventSource.getValue(),
                eventSource.getAccountId());

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
