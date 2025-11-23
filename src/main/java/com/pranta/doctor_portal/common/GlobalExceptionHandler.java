package com.pranta.doctor_portal.common;

import com.pranta.doctor_portal.exception.BadRequestException;
import com.pranta.doctor_portal.exception.InternalServerException;
import com.pranta.doctor_portal.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request
    ) {
        log.warn("BadRequest at {}: {}", request.getRequestURI(), ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            NotFoundException ex,
            HttpServletRequest request
    ) {
        log.warn("NotFound at {}: {}", request.getRequestURI(), ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse("Validation error");

        log.warn("Validation error at {}: {}", request.getRequestURI(), msg);

        ErrorResponse error = ErrorResponse.builder()
                .message(msg)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternal(
            InternalServerException ex,
            HttpServletRequest request
    ) {
        log.error("InternalServerError at {}: {}", request.getRequestURI(), ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(
            Exception ex,
            HttpServletRequest request
    ) {
        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        ErrorResponse error = ErrorResponse.builder()
                .message("Something went wrong. Please try again later.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
