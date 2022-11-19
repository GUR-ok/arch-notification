package ru.gur.archnotification.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {
    private final KafkaTemplate<String, Double> kafkaTemplateString;

    public void sendString(String topic, String key, Double data) {
        log.info("Start send");
                kafkaTemplateString.send(topic, key, data)
                        .addCallback(
                                result -> log.info("send complete {}", topic),
                                fail -> log.error("fail send", fail.getCause()));
        log.info("complete send");
    }
}
