package com.practo.payload;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    @PositiveOrZero(message = "Age must be a non-negative value")
    private int age;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNo;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "Disease is required")
    private String disease;
}

