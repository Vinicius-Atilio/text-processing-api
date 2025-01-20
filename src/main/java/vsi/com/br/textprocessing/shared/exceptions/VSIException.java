package vsi.com.br.textprocessing.shared.exceptions;


import org.springframework.http.HttpStatus;
import vsi.com.br.textprocessing.shared.enums.ErrorType;

public class VSIException extends RuntimeException {
    public ErrorType errorType;
    public HttpStatus httpStatus;
    public String errorCode;
    public String errorMessage;
    public String tracking;

    public VSIException(ErrorType errorType, HttpStatus httpStatus, String errorCode, String errorMessage) {
        super(errorCode + ": " + errorMessage);
        this.errorType = errorType;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public VSIException(ErrorType errorType, String errorCode, String errorMessage, String tracking) {
        super(errorCode + ": " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.tracking = tracking;
    }

    public static VSIException of(ErrorType errorType, HttpStatus httpStatus, String errorCode, String errorMessage) {
        return new VSIException(errorType, httpStatus, errorCode, errorMessage);
    }

    public static VSIException of(ErrorType errorType, String errorCode, String errorMessage, String stackTrace) {
        return new VSIException(errorType, errorCode, errorMessage, stackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getTracking() {
        return tracking;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
