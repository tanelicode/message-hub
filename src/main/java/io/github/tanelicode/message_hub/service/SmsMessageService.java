package io.github.tanelicode.message_hub.service;

import io.github.tanelicode.message_hub.dto.CreateSmsMessageRequest;
import io.github.tanelicode.message_hub.exception.SmsMessageNotFoundException;
import io.github.tanelicode.message_hub.model.SmsMessage;
import io.github.tanelicode.message_hub.model.SmsStatus;
import io.github.tanelicode.message_hub.repository.SmsMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SmsMessageService {

    private final SmsMessageRepository smsMessageRepository;

    public SmsMessageService(SmsMessageRepository smsMessageRepository) {
        this.smsMessageRepository = smsMessageRepository;
    }

    public List<SmsMessage> getAllMessages() {
        return smsMessageRepository.findAll();
    }

    public SmsMessage getMessageById(Long id) {
        return smsMessageRepository.findById(id)
                .orElseThrow(() -> new SmsMessageNotFoundException(id));
    }

    public SmsMessage createMessage(CreateSmsMessageRequest request) {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setPhoneNumber(request.getPhoneNumber());
        smsMessage.setMessageText(request.getMessageText());
        smsMessage.setStatus(SmsStatus.PENDING);
        smsMessage.setCreatedAt(LocalDateTime.now());

        return smsMessageRepository.save(smsMessage);
    }

    public SmsMessage sendMessage(Long id) {
        SmsMessage smsMessage = getMessageById(id);

        smsMessage.setStatus(SmsStatus.SENT);
        smsMessage.setSentAt(LocalDateTime.now());

        return smsMessageRepository.save(smsMessage);
    }
}