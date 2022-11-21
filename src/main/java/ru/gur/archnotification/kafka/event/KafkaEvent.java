package ru.gur.archnotification.kafka.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentFailEventData.class, name = "PAYMENT_FAIL"),
        @JsonSubTypes.Type(value = OrderPaidEventData.class, name = "ORDER_PAID"),
        @JsonSubTypes.Type(value = DepositAcceptedEventData.class, name = "DEPOSIT_ACCEPTED"),
})
public interface KafkaEvent extends EventSource{
}