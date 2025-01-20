package vsi.com.br.textprocessing.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;
import vsi.com.br.textprocessing.shared.response.AppResponse;

@RestControllerAdvice
public class VSIExceptionHandler {
    @ExceptionHandler(VSIException.class)
    protected ResponseEntity<AppResponse<String>> iMarketExHandler(VSIException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(AppResponse.ofError(ex));
    }
}
