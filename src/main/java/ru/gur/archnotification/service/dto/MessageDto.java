package ru.gur.archnotification.service.dto;

import lombok.Builder;
import lombok.Value;
import ru.gur.archnotification.kafka.event.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class MessageDto {

    UUID id;

    UUID account;

    String text;

    Event event;

    LocalDateTime created;
}
