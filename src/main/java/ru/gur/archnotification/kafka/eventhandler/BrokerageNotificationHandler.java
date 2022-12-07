package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.BrokerageNotificationEventData;
import ru.gur.archnotification.kafka.event.DepositAcceptedEventData;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.service.MessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerageNotificationHandler implements EventHandler<BrokerageNotificationEventData> {

    private final MessageService messageService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.BROKERAGE_NOTIFICATION.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final BrokerageNotificationEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        messageService.sendMessage(eventSource.getAccountId(),
                eventSource.getMessage(),
                eventSource.getEvent());

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
