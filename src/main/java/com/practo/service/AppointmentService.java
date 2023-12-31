package com.practo.service;

import com.practo.payload.AppointmentDto;

public interface AppointmentService {
    void bookAppointment(AppointmentDto appointmentDto);
}
