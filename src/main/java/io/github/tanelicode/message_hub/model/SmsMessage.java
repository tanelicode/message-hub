package io.github.tanelicode.message_hub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SmsMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @Column(length = 1000)
    private String messageText;

    @Enumerated(EnumType.STRING)
    private SmsStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;
}