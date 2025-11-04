package br.futurodev.joinville.m3s01.errors;

import br.futurodev.joinville.m3s01.dtos.ErrorResponseDto;
import br.futurodev.joinville.m3s01.errors.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleException(BadCredentialsException exception) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(NotFoundException exception) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            MethodArgumentNotValidException exception
    ) {
        List<String> messages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            messages.add(error.getField() + ": " + error.getDefaultMessage());
        });
        String errorMessage = String.join("; ", messages);

        return buildResponseEntity(HttpStatus.BAD_REQUEST, exception, errorMessage);
    }


    private ResponseEntity<ErrorResponseDto> buildResponseEntity(
            HttpStatus httpStatus,
            Exception exception
    ) {
        return buildResponseEntity(httpStatus, exception, exception.getMessage());
    }
    private ResponseEntity<ErrorResponseDto> buildResponseEntity(
            HttpStatus httpStatus,
            Exception exception,
            String message
    ) {
        return ResponseEntity
                .status(httpStatus)
                .body(
                        ErrorResponseDto.builder()
                                .code(String.valueOf(httpStatus.value()))
                                .message(message)
                                .className(exception.getClass().getSimpleName())
                                .build()
                );
    }

}
