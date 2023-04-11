package com.nobrega.poc.customer.commons.infra;

import com.nobrega.poc.customer.commons.domain.error.ApplicationErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static com.nobrega.poc.customer.commons.domain.error.CommonsErrorCode.ARGUMENT_NOT_VALID;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApplicationErrorResponse> handleServiceException(
            ServiceException exception,
            WebRequest request) {

        var applicationErrorResponse = ApplicationErrorResponse.builder()
                .applicationErrorCode(exception.getApplicationErrorCode())
                .error(exception.getApplicationErrorCode().getCode())
                .status(exception.getApplicationErrorCode().getStatus().value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now().format(ISO_DATE_TIME))
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        log.info("m=handleServiceException applicationErrorResponse: {} ", applicationErrorResponse);
        return new ResponseEntity<>(applicationErrorResponse, getHttpHeaders(), exception.getApplicationErrorCode().getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        final var applicationErrorResponse = ApplicationErrorResponse.builder()
                .applicationErrorCode(ARGUMENT_NOT_VALID)
                .error(ARGUMENT_NOT_VALID.getCode())
                .status(ARGUMENT_NOT_VALID.getStatus().value())
                .message(ARGUMENT_NOT_VALID.getMessage())
                .timestamp(LocalDateTime.now().format(ISO_DATE_TIME))
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .details(exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError ->
                                new StringBuilder()
                                        .append(fieldError.getField())
                                        .append(" ")
                                        .append(fieldError.getDefaultMessage())
                                        .toString())
                        .toList())
                .build();
        log.info("m=handleMethodArgumentNotValid applicationErrorResponse: {} ", applicationErrorResponse);
        return new ResponseEntity<>(applicationErrorResponse, status);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
