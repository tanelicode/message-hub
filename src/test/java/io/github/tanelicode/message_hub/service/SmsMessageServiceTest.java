package io.github.tanelicode.message_hub.service;

import io.github.tanelicode.message_hub.dto.CreateSmsMessageRequest;
import io.github.tanelicode.message_hub.exception.SmsMessageNotFoundException;
import io.github.tanelicode.message_hub.model.SmsMessage;
import io.github.tanelicode.message_hub.model.SmsStatus;
import io.github.tanelicode.message_hub.repository.SmsMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SmsMessageServiceTest {

    @Mock
    private SmsMessageRepository smsMessageRepository;

    @InjectMocks
    private SmsMessageService smsMessageService;

    @Test
    void createMessage_shouldCreateSmsWithPendingStatus() {
        CreateSmsMessageRequest request = new CreateSmsMessageRequest();
        request.setPhoneNumber("+491701234567");
        request.setMessageText("Testnachricht");

        SmsMessage savedMessage = new SmsMessage();
        savedMessage.setId(1L);
        savedMessage.setPhoneNumber(request.getPhoneNumber());
        savedMessage.setMessageText(request.getMessageText());
        savedMessage.setStatus(SmsStatus.PENDING);

        when(smsMessageRepository.save(any(SmsMessage.class))).thenReturn(savedMessage);

        SmsMessage result = smsMessageService.createMessage(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("+491701234567", result.getPhoneNumber());
        assertEquals("Testnachricht", result.getMessageText());
        assertEquals(SmsStatus.PENDING, result.getStatus());

        verify(smsMessageRepository, times(1)).save(any(SmsMessage.class));
    }

    @Test
    void getMessageById_shouldReturnSmsMessage_whenMessageExists() {
        SmsMessage existingMessage = new SmsMessage();
        existingMessage.setId(1L);
        existingMessage.setPhoneNumber("+491701234567");
        existingMessage.setMessageText("Vorhandene SMS");
        existingMessage.setStatus(SmsStatus.PENDING);

        when(smsMessageRepository.findById(1L)).thenReturn(Optional.of(existingMessage));

        SmsMessage result = smsMessageService.getMessageById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Vorhandene SMS", result.getMessageText());

        verify(smsMessageRepository, times(1)).findById(1L);
    }

    @Test
    void getMessageById_shouldThrowException_whenMessageDoesNotExist() {
        when(smsMessageRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(SmsMessageNotFoundException.class, () -> {
            smsMessageService.getMessageById(999L);
        });

        verify(smsMessageRepository, times(1)).findById(999L);
    }

    @Test
    void sendMessage_shouldSetStatusToSentAndSetSentAt() {
        SmsMessage existingMessage = new SmsMessage();
        existingMessage.setId(1L);
        existingMessage.setPhoneNumber("+491701234567");
        existingMessage.setMessageText("SMS zum Senden");
        existingMessage.setStatus(SmsStatus.PENDING);

        when(smsMessageRepository.findById(1L)).thenReturn(Optional.of(existingMessage));
        when(smsMessageRepository.save(any(SmsMessage.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SmsMessage result = smsMessageService.sendMessage(1L);

        assertEquals(SmsStatus.SENT, result.getStatus());
        assertNotNull(result.getSentAt());

        verify(smsMessageRepository, times(1)).findById(1L);
        verify(smsMessageRepository, times(1)).save(existingMessage);
    }

    @Test
    void failMessage_shouldSetStatusToFailed() {
        SmsMessage existingMessage = new SmsMessage();
        existingMessage.setId(1L);
        existingMessage.setPhoneNumber("+491701234567");
        existingMessage.setMessageText("SMS mit Fehler");
        existingMessage.setStatus(SmsStatus.PENDING);

        when(smsMessageRepository.findById(1L)).thenReturn(Optional.of(existingMessage));
        when(smsMessageRepository.save(any(SmsMessage.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SmsMessage result = smsMessageService.failMessage(1L);

        assertEquals(SmsStatus.FAILED, result.getStatus());

        verify(smsMessageRepository, times(1)).findById(1L);
        verify(smsMessageRepository, times(1)).save(existingMessage);
    }
}