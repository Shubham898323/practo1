package com.practo.controller;


import com.practo.payload.DoctorWithReviewsDto;
import com.practo.payload.ReviewDto;
import com.practo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //http://localhost:8080/api/reviews
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody @Valid ReviewDto reviewDto, BindingResult result) {
        if (result.hasErrors()) {

            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
        }
        ReviewDto dto = reviewService.addReview(reviewDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


//http://localhost:8080/api/reviews/doctor/{doctorId}
@GetMapping("/doctor/{doctorId}")
public ResponseEntity<List<DoctorWithReviewsDto>> getReviewsByDoctorId(@PathVariable long doctorId) {
    List<DoctorWithReviewsDto> reviewsByDoctorId = reviewService.getReviewsByDoctorId(doctorId);

    // Calculate average rating and rating percentage for each doctor
    for (DoctorWithReviewsDto doctor : reviewsByDoctorId) {
        doctor.setAverageRating(calculateAverageRating(doctor.getReviews()));
        doctor.setRatingPercentage(calculateRatingPercentage(doctor.getAverageRating()));
    }

    return new ResponseEntity<>(reviewsByDoctorId, HttpStatus.OK);
}
    private double calculateAverageRating(List<ReviewDto> reviews) {

        double totalRating = 0.0;
        for (ReviewDto review : reviews) {
            totalRating += review.getReview();
        }
        return totalRating / reviews.size();
    }
    private double calculateRatingPercentage(double averageRating) {
        return (averageRating / 5.0) * 100.0;
    }
}


