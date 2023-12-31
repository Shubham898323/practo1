package com.practo.service;

import com.practo.payload.DoctorWithReviewsDto;
import com.practo.payload.ReviewDto;

import java.util.List;


public interface ReviewService {
    ReviewDto addReview(ReviewDto reviewDto);
    List<DoctorWithReviewsDto> getReviewsByDoctorId(long doctorId);





}
