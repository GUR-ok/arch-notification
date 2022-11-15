package ru.gur.archnotification.web.healthcheck;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archnotification.web.healthcheck.response.Health;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class HealthCheck {


    @GetMapping("/health")
    public Health health() {

        return Health.builder()
                .status(Health.HealthStatus.OK)
                .build();
    }
}
