package com.practo.controller;

import com.practo.payload.DoctorDto;
import com.practo.payload.PatientDto;
import com.practo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    //http://localhost:8080/api/patients
    @PostMapping
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientDto patientDto, BindingResult result) {
      if(result.hasErrors()){
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
      }
        PatientDto dto = patientService.addPatient(patientDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }}