package io.github.tanelicode.message_hub.exception;

public class SmsMessageNotFoundException extends RuntimeException {

    public SmsMessageNotFoundException(Long id) {
        super("SMS mit ID " + id + " wurde nicht gefunden");
    }
}