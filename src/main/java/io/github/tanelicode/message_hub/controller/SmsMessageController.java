package io.github.tanelicode.message_hub.controller;

import io.github.tanelicode.message_hub.dto.CreateSmsMessageRequest;
import io.github.tanelicode.message_hub.model.SmsMessage;
import io.github.tanelicode.message_hub.service.SmsMessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsMessageController {

    private final SmsMessageService smsMessageService;

    public SmsMessageController(SmsMessageService smsMessageService) {
        this.smsMessageService = smsMessageService;
    }

    @GetMapping
    public List<SmsMessage> getAllMessages() {
        return smsMessageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public SmsMessage getMessageById(@PathVariable Long id) {
        return smsMessageService.getMessageById(id);
    }

    @PostMapping
    public SmsMessage createMessage(@Valid @RequestBody CreateSmsMessageRequest request) {
        return smsMessageService.createMessage(request);
    }

    @PutMapping("/{id}/send")
    public SmsMessage sendMessage(@PathVariable Long id) {
        return smsMessageService.sendMessage(id);
    }

    @PutMapping("/{id}/fail")
    public SmsMessage failMessage(@PathVariable Long id) {
        return smsMessageService.failMessage(id);
    }

}