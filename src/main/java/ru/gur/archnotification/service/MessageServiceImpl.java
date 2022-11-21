package ru.gur.archnotification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gur.archnotification.entity.Message;
import ru.gur.archnotification.kafka.event.Event;
import ru.gur.archnotification.persistence.MessageRepository;
import ru.gur.archnotification.service.dto.MessageDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public MessageDto read(final UUID id) {
        final Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found"));

        return MessageDto.builder()
                .id(message.getId())
                .account(message.getAccount())
                .text(message.getText())
                .created(message.getCreated())
                .event(message.getEvent())
                .build();
    }

    @Override
    public MessageDto findByAccountId(final UUID accountId) {
        final Message message = messageRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("message not found"));

        return MessageDto.builder()
                .id(message.getId())
                .account(message.getAccount())
                .text(message.getText())
                .created(message.getCreated())
                .event(message.getEvent())
                .build();
    }

    @Override
    public List<MessageDto> getMessages() {
        return messageRepository.findAll().stream()
                .map(m -> MessageDto.builder()
                        .id(m.getId())
                        .account(m.getAccount())
                        .text(m.getText())
                        .created(m.getCreated())
                        .event(m.getEvent())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UUID sendMessage(final UUID account, final String text, final Event event) {
        final Message message = new Message();
        message.setAccount(account);
        message.setText(text);
        message.setEvent(event);

        messageRepository.save(message);

        return message.getId();
    }
}
