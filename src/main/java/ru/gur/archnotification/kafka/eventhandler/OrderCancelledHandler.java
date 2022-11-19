package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.kafka.event.OrderCancelledEventData;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCancelledHandler implements EventHandler<OrderCancelledEventData> {

//    private final service;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.ORDER_CANCEL.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final OrderCancelledEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

//        service.call

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
