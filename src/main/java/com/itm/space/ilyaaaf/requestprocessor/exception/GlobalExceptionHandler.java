package com.itm.space.ilyaaaf.requestprocessor.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itm.space.ilyaaaf.requestprocessor.model.response.HttpErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        String errorMessage = String.join(", ", errorMessages);
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage, ex);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Неверный формат JSON в теле запроса";

        if (ex.getCause() instanceof InvalidFormatException err && err.getTargetType() != null && err.getTargetType().isEnum()) {
            errorMessage = String.format("Недопустимое значение '%s'. Ожидались только: %s",
                    err.getValue(),
                    Arrays.toString(err.getTargetType().getEnumConstants()));
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpErrorResponse> handleIllegalArgumentException(Exception ex) {

        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponse> handleGeneralException(Exception ex) {

        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Неизвестная ошибка сервера. Попробуйте снова",
                ex);
    }

    private ResponseEntity<HttpErrorResponse> buildErrorResponse(
            HttpStatus status,
            String type,
            String message,
            Exception ex
    ) {
        log.error(type, ex);

        HttpErrorResponse errorResponse = new HttpErrorResponse(
                status.value(),
                type,
                message
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}
