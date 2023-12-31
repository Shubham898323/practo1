package com.practo.service;

import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.entity.Review;
import com.practo.exception.ResourceNotFound;

import com.practo.payload.DoctorWithReviewsDto;
import com.practo.payload.ReviewDto;
import com.practo.repository.DoctorRepository;
import com.practo.repository.PatientRepository;
import com.practo.repository.ReviewRepository;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;


    public ReviewServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        // Find the Doctor
        Doctor doctor = doctorRepository.findById(reviewDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFound("Doctor not found with id " + reviewDto.getDoctorId()));

        // Find the Patient
        Patient patient = patientRepository.findById(reviewDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFound("Patient not found with id " + reviewDto.getPatientId()));

        // Use ModelMapper to map ReviewDto to Review
        Review review = modelMapper.map(reviewDto, Review.class);

        // Save the Review
        Review savedReview = reviewRepository.save(review);

        // Map the saved Review back to ReviewDto
        ReviewDto savedReviewDto = modelMapper.map(savedReview, ReviewDto.class);

        return savedReviewDto;
    }


//    @Override
//    public List<DoctorWithReviewsDto> getReviewsByDoctorId(long doctorId) {
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
//
//        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
//
//        return List.of(mapToDoctorWithReviewsDto(doctor, reviews));
//    }
//
//    private DoctorWithReviewsDto mapToDoctorWithReviewsDto(Doctor doctor, List<Review> reviews) {
//        DoctorWithReviewsDto doctorWithReviewsDto = modelMapper.map(doctor, DoctorWithReviewsDto.class);
//        List<ReviewDto> reviewDtos = reviews.stream()
//                .map(review -> modelMapper.map(review, ReviewDto.class))
//                .collect(Collectors.toList());
//        doctorWithReviewsDto.setReviews(reviewDtos);
//        return doctorWithReviewsDto;
//    }
//
//}

    @Override
    public List<DoctorWithReviewsDto> getReviewsByDoctorId(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFound("Doctor not found with id: " + doctorId));

        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);

        return List.of(mapToDoctorWithReviewsDto(doctor, reviews));
    }

    private DoctorWithReviewsDto mapToDoctorWithReviewsDto(Doctor doctor, List<Review> reviews) {
        DoctorWithReviewsDto doctorWithReviewsDto = modelMapper.map(doctor, DoctorWithReviewsDto.class);
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
        doctorWithReviewsDto.setReviews(reviewDtos);
        return doctorWithReviewsDto;
    }

}




