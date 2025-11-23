package com.pranta.doctor_portal.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentResponse create(AppointmentRequest request) {

        Appointment ap = Appointment.builder()
                .patientName(request.getPatientName())
                .patientEmail(request.getPatientEmail())
                .patientPhone(request.getPatientPhone())
                .doctorName(request.getDoctorName())
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .status("PENDING")
                .build();

        repository.save(ap);

        return AppointmentResponse.builder()
                .id(ap.getId())
                .message("Appointment created successfully")
                .build();
    }
}
