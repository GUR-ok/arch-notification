package ru.gur.archnotification.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import ru.gur.archnotification.kafka.event.Event;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "account", nullable = false)
    private UUID account;

    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    @Column(name = "event", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Event event;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;
}
