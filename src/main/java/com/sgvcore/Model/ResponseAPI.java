package com.sgvcore.Model;

public class ResponseAPI {
    private final boolean success;
    private final String status_code;
    private final String message;
    private final Object data;

    public ResponseAPI(boolean success, String status_code, String message, Object data) {
        this.success = success;
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
