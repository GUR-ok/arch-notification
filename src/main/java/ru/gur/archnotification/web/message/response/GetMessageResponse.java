package ru.gur.archnotification.web.message.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetMessageResponse {

    private UUID id;

    private UUID account;

    private String text;
}
