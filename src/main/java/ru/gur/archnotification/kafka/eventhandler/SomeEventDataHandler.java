package ru.gur.archnotification.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archnotification.kafka.Event;
import ru.gur.archnotification.kafka.EventSource;
import ru.gur.archnotification.kafka.Producer;
import ru.gur.archnotification.kafka.SomeEventData;

@Slf4j
@Component
@RequiredArgsConstructor
public class SomeEventDataHandler implements EventHandler<SomeEventData> {

    private final Producer producer;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.SOME.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final SomeEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        producer.sendString("KeyValueTopic", eventSource.getId().toString(), eventSource.getValue());
        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}