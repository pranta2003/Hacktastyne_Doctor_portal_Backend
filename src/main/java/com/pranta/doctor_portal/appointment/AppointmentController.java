package com.pranta.doctor_portal.appointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public AppointmentResponse create(@RequestBody AppointmentRequest request) {
        log.info("Creating appointment...");
        return service.create(request);
    }

    @GetMapping
    public String testGet() {
        return "GET working";
    }
}
