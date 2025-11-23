package com.pranta.doctor_portal.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
}
