package ru.gur.archnotification.service;

import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.service.dto.MessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    MessageDto read(UUID id);

    MessageDto findByAccountId(UUID accountId);

    List<MessageDto> getMessages();

    UUID sendMessage(UUID account, String text, Event event);
}
