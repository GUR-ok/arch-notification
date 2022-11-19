package ru.gur.archnotification.kafka.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class OrderCreatedEventData implements KafkaEvent {

    UUID orderId;

    Double price;

    @Override
    public Event getEvent() {
        return Event.ORDER_CREATED;
    }
}
