package com.practo.service;


import com.practo.entity.Patient;
import com.practo.payload.PatientDto;
import com.practo.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
       Patient patient = modelMapper.map(patientDto, Patient.class);
        Patient savedData = patientRepository.save(patient);
        return modelMapper.map(savedData, PatientDto.class);
    }
}
