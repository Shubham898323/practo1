package com.practo.payload;

import com.practo.entity.Review;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;

    @NotEmpty(message = "Doctor name is required")
    private String doctorName;

    @NotEmpty(message = "Specialization is required")
    private String specialization;

    @PositiveOrZero(message = "Experience must be a non-negative value")
    private int experience;

    @DecimalMin(value = "0.0", message = "Ratings must be a non-negative value")
    @DecimalMax(value = "5.0", message = "Ratings cannot be more than 5.0")
    private double ratings;

    @NotEmpty(message = "Qualification is required")
    private String qualification;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;




}
