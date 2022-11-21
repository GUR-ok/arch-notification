package ru.gur.archnotification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gur.archnotification.persistence.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
}
