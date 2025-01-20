package vsi.com.br.textprocessing.shared.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;

import java.util.function.Supplier;

public class AppResponse<T> {

    private boolean success;
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    @JsonProperty("return")
    private T data;


    public AppResponse() {}

    public AppResponse(HttpStatus status, T data) {
        this.success = true;
        this.status = status;
        this.data = data;
    }

    public AppResponse(HttpStatus status, String errorCode, String errorMessage) {
        this.success = false;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public AppResponse(boolean success, HttpStatus status, String errorCode,
                       String errorMessage, T data) {
        this.success = success;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> AppResponse<T> Execute(Supplier<T> supplier) {
        try {
            T result = supplier.get();
            return AppResponse.ofSuccess(result);
        } catch (Error error) {
            return AppResponse.ofUnexpectedError();
        }
    }

    public static <T> AppResponse<T> ofSuccess(T data) {
        return new AppResponse<>(HttpStatus.OK, data);
    }

    public static <T> AppResponse<T> ofError(VSIException exception) {
        return new AppResponse<>(exception.getHttpStatus(), exception.getErrorCode(), exception.getErrorMessage());
    }

    public static <T> AppResponse<T> ofUnexpectedError() {
        return new AppResponse<>(HttpStatus.OK, "UNEXPECTED_ERROR", "An Error Occurred While Processing Request");
    }

    public String toStringSucess() {
        return "{" +
            "\"success\"" + ":" + success + "," +
            "\"status\"" + ":" + "\"" + status.getReasonPhrase() + "\"" + "," +
            "\"return\"" + ":" + data.toString() +
            '}';
    }

    public String toStringError() {
        return "{" +
            "\"success\"" + ":" + success + "," +
            "\"status\"" + ":" + "\"" + status.getReasonPhrase()
                .toUpperCase().replace(" ", "_") + "\"" + "," +
            "\"errorCode\"" + ":" + "\"" + errorCode + "\""  + "," +
            "\"errorMessage\"" + ":" + "\"" + errorMessage + "\"" +
            '}';
    }
}
