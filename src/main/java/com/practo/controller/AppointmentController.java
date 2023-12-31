package com.practo.controller;

import com.practo.entity.Appointment;
import com.practo.payload.AppointmentDto;
import com.practo.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //http://localhost:8080/api/appointments/book
    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            appointmentService.bookAppointment(appointmentDto);
            return new ResponseEntity<>("Appointment booked successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
