package com.practo.repository;

import com.practo.entity.Review;
import com.practo.payload.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByDoctorId(long doctorId);

}
