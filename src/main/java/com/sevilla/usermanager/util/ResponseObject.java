package com.sevilla.usermanager.util;

import com.sevilla.usermanager.enums.StatusResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseObject {
    private String status;

    private HttpStatus code;

    private Object data;

    private String message;

    public int getCode() {
        return code.value();
    }

    public void badResponse(Object data, String message) {
        this.data = data;
        this.badResponse(message);
    }

    public void badResponse(String message) {
        this.status = StatusResponse.ERROR.toString();
        this.code = HttpStatus.BAD_REQUEST;
        this.message = message;
    }

    public void notFoundResponse(String message) {
        this.status = StatusResponse.NOT_FOUND.toString();
        this.code = HttpStatus.NOT_FOUND;
        this.message = message;
    }

    public void unAuthorizedResponse(String message) {
        this.status = StatusResponse.UNAUTHORIZED.toString();
        this.code = HttpStatus.UNAUTHORIZED;
        this.message = message;
    }

    public void successResponse(Object data, String message) {
        this.data = data;
        this.successResponse(message);
    }

    public void successResponse(String message) {
        this.status = StatusResponse.SUCCESS.toString();
        this.code = HttpStatus.OK;
        this.message = message;
    }

    public void successResponse(Object data) {
        this.status = StatusResponse.SUCCESS.toString();
        this.code = HttpStatus.OK;
        this.data = data;
    }

    public void createdResponse(Object data, String message) {
        this.data = data;
        this.createdResponse(message);
    }

    public void createdResponse(String message) {
        this.status = StatusResponse.SUCCESS.toString();
        this.code = HttpStatus.CREATED;
        this.message = message;
    }
}
