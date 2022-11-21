package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.kafka.event.PaymentFailEventData;
import ru.gur.archnotification.service.MessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentFailHandler implements EventHandler<PaymentFailEventData> {

    private final MessageService messageService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.PAYMENT_FAIL.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final PaymentFailEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        messageService.sendMessage(eventSource.getAccountId(),
                String.format("NOT ENOUGH MONEY on account %s for order %s",
                        eventSource.getAccountId(), eventSource.getOrderId()),
                eventSource.getEvent());

        System.out.printf("!!! NOT ENOUGH MONEY on account %s for order %s\n",
                eventSource.getAccountId(), eventSource.getOrderId());

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
