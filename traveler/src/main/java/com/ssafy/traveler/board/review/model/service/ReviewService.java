package com.ssafy.traveler.board.review.model.service;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {

    List<ReviewDto> selectAllReviews() throws SQLException;

    void writeReview(ReviewDto reviewDto) throws SQLException;

    ReviewDto getReviewDetail(int no) throws SQLException;

    void updateReview(ReviewDto reviewDto) throws SQLException;

    void deleteReview(int no) throws SQLException;
}