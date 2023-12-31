package com.practo.service;

import com.practo.entity.Doctor;
import com.practo.exception.ResourceNotFound;
import com.practo.payload.DoctorDto;
import com.practo.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DoctorServiceImpl implements DoctorService {

    private  DoctorRepository doctorRepository;
    private  ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        Doctor savedData = doctorRepository.save(doctor);
        return modelMapper.map(savedData, DoctorDto.class);
    }


        @Override
        public List<DoctorDto> searchByDoctorNameOrSpecialization(String doctorName, String specialization) {
            List<Doctor> doctors = doctorRepository.searchByDoctorNameOrSpecialization(doctorName, specialization);

            if (doctors.isEmpty()) {
                throw new ResourceNotFound("No matching doctors found");
            }

            return doctors.stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
        }
    @Override
    public DoctorDto getDoctorDtoById(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            return modelMapper.map(doctor, DoctorDto.class);
        } else {
            // Handle the case when the doctor is not found
            throw new ResourceNotFound("Doctor not found with ID: " + doctorId);
        }
    }
    }










