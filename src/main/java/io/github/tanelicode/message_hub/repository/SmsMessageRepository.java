package io.github.tanelicode.message_hub.repository;

import io.github.tanelicode.message_hub.model.SmsMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsMessageRepository extends JpaRepository<SmsMessage, Long> {
}