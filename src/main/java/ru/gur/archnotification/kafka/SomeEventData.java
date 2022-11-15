package ru.gur.archnotification.kafka;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SomeEventData implements KafkaEvent {

    UUID id;

    Double value;

    @Override
    public Event getEvent() {
        return Event.SOME;
    }
}
