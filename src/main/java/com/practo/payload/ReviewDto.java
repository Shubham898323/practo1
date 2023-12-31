package com.practo.payload;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private long id;

    @NotNull(message = "Doctor ID cannot be null")
    @Column(name = "doctor_id")
    private Long doctorId;

    @NotNull(message = "Patient ID cannot be null")
    @Column(name = "patient_id")
    private long patientId;

    @NotNull(message = "Review cannot be null")
    @Min(value = 0, message = "Review should not be less than 0")
    @Max(value = 5, message = "Review should not be greater than 5")
    @Column(name = "review")
    private Double review;  // Use Double instead of double for @NotNull



}