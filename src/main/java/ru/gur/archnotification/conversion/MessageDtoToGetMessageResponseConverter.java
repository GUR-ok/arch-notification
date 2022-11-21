package ru.gur.archnotification.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.gur.archnotification.service.dto.MessageDto;
import ru.gur.archnotification.web.message.response.GetMessageResponse;

@Component
public class MessageDtoToGetMessageResponseConverter implements Converter<MessageDto, GetMessageResponse> {

    @Override
    public GetMessageResponse convert(final MessageDto source) {
        return GetMessageResponse.builder()
                .id(source.getId())
                .account(source.getAccount())
                .text(source.getText())
                .build();
    }
}
