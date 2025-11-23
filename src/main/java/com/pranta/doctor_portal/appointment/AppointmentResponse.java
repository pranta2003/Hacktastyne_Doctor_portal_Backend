package com.pranta.doctor_portal.appointment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {
    private Long id;
    private String message;
}
