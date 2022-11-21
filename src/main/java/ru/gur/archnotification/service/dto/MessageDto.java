package ru.gur.archnotification.service.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class MessageDto {

    UUID id;

    UUID account;

    String text;
}
