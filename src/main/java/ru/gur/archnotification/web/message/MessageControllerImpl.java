package ru.gur.archnotification.web.message;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archnotification.service.MessageService;
import ru.gur.archnotification.web.message.response.GetMessageResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MessageControllerImpl implements MessageController {

    private final MessageService messageService;

    private final ConversionService conversionService;

    @Override
    @GetMapping(path = "/{id}")
    public GetMessageResponse read(final UUID id) {
        return conversionService.convert(messageService.read(id), GetMessageResponse.class);
    }

    @Override
    @GetMapping(path = "/account/{id}")
    public GetMessageResponse findByAccountId(final UUID accountId) {
        return conversionService.convert(messageService.read(accountId), GetMessageResponse.class);
    }

    @Override
    @GetMapping
    public List<GetMessageResponse> getMessages() {
        return messageService.getMessages().stream()
                .map(m -> conversionService.convert(m, GetMessageResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @DeleteMapping
    public void clear() {
        messageService.deleteAll();
    }
}
