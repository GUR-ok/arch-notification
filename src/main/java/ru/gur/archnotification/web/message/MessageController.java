package ru.gur.archnotification.web.message;

import ru.gur.archnotification.web.message.response.GetMessageResponse;

import java.util.List;
import java.util.UUID;

public interface MessageController {

    GetMessageResponse read(UUID id);

    GetMessageResponse findByAccountId(UUID accountId);

    List<GetMessageResponse> getMessages();

    void clear();
}
