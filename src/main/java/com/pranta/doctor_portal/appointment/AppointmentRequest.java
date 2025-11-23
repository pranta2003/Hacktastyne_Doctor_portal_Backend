package com.pranta.doctor_portal.appointment;

import lombok.Data;

@Data
public class AppointmentRequest {
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private String doctorName;
    private String appointmentDate;
    private String appointmentTime;
}
