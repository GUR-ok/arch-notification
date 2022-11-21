package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.kafka.event.OrderPaidEventData;
import ru.gur.archnotification.service.MessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaidHandler implements EventHandler<OrderPaidEventData> {

    private final MessageService messageService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.ORDER_PAID.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final OrderPaidEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        messageService.sendMessage(eventSource.getAccountId(),
                String.format("ORDER %s PAID SUCCESSFULLY", eventSource.getOrderId()),
                eventSource.getEvent());

        System.out.printf("!!! ORDER %s PAID SUCCESSFULLY\n", eventSource.getOrderId());

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
