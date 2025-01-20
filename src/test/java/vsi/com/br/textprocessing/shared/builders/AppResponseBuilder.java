package vsi.com.br.textprocessing.shared.builders;

import org.springframework.http.HttpStatus;
import vsi.com.br.textprocessing.shared.response.AppResponse;

public class AppResponseBuilder<T> {
    private boolean success;
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    private T data;


    public AppResponseBuilder<T> withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public AppResponseBuilder<T> withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public AppResponseBuilder<T> withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public AppResponseBuilder<T> withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public AppResponseBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    public AppResponse<T> build() {
        return new AppResponse<>(this.success, this.status, this.errorCode, this.errorMessage, this.data);
    }
}
