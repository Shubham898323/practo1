package com.practo.service;

import com.practo.entity.Appointment;
import com.practo.payload.AppointmentDto;
import com.practo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private AppointmentRepository appointmentRepository;

    public void bookAppointment(AppointmentDto appointmentDto) {
        List<String> availableTimeSlots = new ArrayList<>();
        availableTimeSlots.add("10.15 AM");
        availableTimeSlots.add("11.15 AM");
        availableTimeSlots.add("12.15 PM");
        Appointment appointment =new Appointment();
        for (String slots:availableTimeSlots) {
            if(slots.equals(appointmentDto.getBookingTime())) {
                appointment.setBookingTime(LocalTime.parse(appointmentDto.getBookingTime()));
                availableTimeSlots.remove(slots);
            }}

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
                System.out.println("Executing code every 24 hours...");
                availableTimeSlots.add("10.15 AM");
                availableTimeSlots.add("11.15 AM");
                availableTimeSlots.add("12.15 PM");
            }, 0,24, TimeUnit.HOURS);
                appointment.setDoctorId(appointmentDto.getDoctorId());
                appointment.setPatientId(appointmentDto.getPatientId());
                if(appointment.getBookingTime()!=null){
                    appointmentRepository.save(appointment);
            }
                else {
                    System.out.println("Time slot not available");
                }

        }

//        if (availableTimeSlots.contains(appointmentDto.getBookingTime())) {
//            Appointment appointment = new Appointment();
//            appointment.setBookingTime(LocalTime.parse(appointmentDto.getBookingTime()));
//            appointment.setDoctorId(appointmentDto.getDoctorId());
//            appointment.setPatientId(appointmentDto.getPatientId());
//
//            // Save the appointment to the database
//            appointmentRepository.save(appointment);
//
//            // Remove the booked time slot
//            availableTimeSlots.remove(appointmentDto.getBookingTime());
//        } else {
//            // Handle the case when the selected time slot is not available
//            throw new RuntimeException("Selected time slot is not available");
//        }
    }

