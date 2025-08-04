package com.canaslan.exception;

import com.canaslan.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String ofStatic;

    private MessageType messageType;

    public String prepareErrorMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMessage());

        if (this.ofStatic != null) {
            builder.append(" : ").append(ofStatic);
        }

        return builder.toString();
    }
}
