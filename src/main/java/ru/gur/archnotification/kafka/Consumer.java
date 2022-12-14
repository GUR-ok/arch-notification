package ru.gur.archnotification.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.gur.archnotification.kafka.event.EventSource;
import ru.gur.archnotification.kafka.event.KafkaEvent;
import ru.gur.archnotification.kafka.eventhandler.EventHandler;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final Set<EventHandler<EventSource>> eventHandlers;

    @KafkaListener(topics = "payment", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenGroupTopic1(String message) {
        log.info("Receive message: {}", message);

        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final KafkaEvent eventSource = objectMapper.readValue(message, KafkaEvent.class);
            log.info("EventSource: {}", eventSource);

            eventHandlers.stream()
                    .filter(eventSourceEventHandler -> eventSourceEventHandler.canHandle(eventSource))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Handler for eventsource not found"))
                    .handleEvent(eventSource);

        } catch (JsonProcessingException e) {
            log.error("Couldn't parse message: {}; exception: ", message, e);
        }
    }

    @KafkaListener(topics = "brokerage-notification", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenGroupTopic2(String message) {
        log.info("Receive message: {}", message);

        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final KafkaEvent eventSource = objectMapper.readValue(message, KafkaEvent.class);
            log.info("EventSource: {}", eventSource);

            eventHandlers.stream()
                    .filter(eventSourceEventHandler -> eventSourceEventHandler.canHandle(eventSource))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Handler for eventsource not found"))
                    .handleEvent(eventSource);

        } catch (JsonProcessingException e) {
            log.error("Couldn't parse message: {}; exception: ", message, e);
        }
    }
}
