package com.pranta.doctor_portal.appointment;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_name")
    private String patientName;

    @Column(name="patient_email")
    private String patientEmail;

    @Column(name="patient_phone")
    private String patientPhone;

    @Column(name="doctor_name")
    private String doctorName;

    @Column(name="appointment_date")
    private String appointmentDate;

    @Column(name="appointment_time")
    private String appointmentTime;

    @Column(name="status")
    private String status = "PENDING";
}
