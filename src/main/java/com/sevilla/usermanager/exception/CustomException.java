package com.sevilla.usermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomException extends Exception {
    private String mensaje;
    public CustomException() {
        super();
    }
    public CustomException(String mensaje) {
        super();
        this.mensaje = mensaje;
    }
}
