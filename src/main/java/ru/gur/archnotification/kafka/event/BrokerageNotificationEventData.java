package ru.gur.archnotification.kafka.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BrokerageNotificationEventData implements KafkaEvent {

    UUID accountId;

    String message;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.BROKERAGE_NOTIFICATION;
    }
}
