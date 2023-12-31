package com.practo.payload;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWithReviewsDto {
    private Long id;
    private String doctorName;
    private String specialization;
    private String qualification;
    private String description;
    private double ratings;
    private List<ReviewDto> reviews;

    private double RatingPercentage;
    private double AverageRating;
}

