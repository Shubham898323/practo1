package com.practo.repository;

import com.practo.entity.Doctor;
import com.practo.payload.DoctorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT d FROM Doctor d " +
            "WHERE (:doctorName IS NULL OR d.doctorName = :doctorName) " +
            "AND (:specialization IS NULL OR d.specialization = :specialization)")
    List<Doctor> searchByDoctorNameOrSpecialization(
            @Param("doctorName") String doctorName,
            @Param("specialization") String specialization);
}