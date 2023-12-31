package com.practo.controller;


import com.practo.payload.DoctorDto;
import com.practo.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //http://localhost:8080/api/doctors
    @PostMapping
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorDto doctorDto, BindingResult result) {
      if(result.hasErrors()){
          return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
      }
        DoctorDto dto = doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/doctors/search?doctorName=Dr.Kiran&specialization=Dermatologist
    //http://localhost:8080/api/doctors/search?doctorName=
    //http://localhost:8080/api/doctors/search?specialization=

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDto>> searchByDoctorNameOrSpecialization(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String specialization) {
        List<DoctorDto> doctorDtoList = doctorService.searchByDoctorNameOrSpecialization(doctorName, specialization);
        return new ResponseEntity<>(doctorDtoList, HttpStatus.OK);
    }}