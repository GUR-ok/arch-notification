package ru.gur.archnotification.kafka.eventhandler;

import ru.gur.archnotification.kafka.event.EventSource;

public interface EventHandler<T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
