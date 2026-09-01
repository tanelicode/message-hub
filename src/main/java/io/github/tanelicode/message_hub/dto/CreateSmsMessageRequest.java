package io.github.tanelicode.message_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSmsMessageRequest {

    @NotBlank(message = "Telefonnummer darf nicht leer sein")
    private String phoneNumber;

    @NotBlank(message = "Nachrichtentext darf nicht leer sein")
    @Size(max = 1000, message = "Nachrichtentext darf maximal 1000 Zeichen lang sein")
    private String messageText;
}