package com.ssafy.traveler.board.review.model.service;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import com.ssafy.traveler.board.review.model.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> selectAllReviews() throws SQLException {
        return reviewMapper.selectAllReviews();
    }

    @Override
    public void writeReview(ReviewDto reviewDto) throws SQLException {
        reviewMapper.writeReview(reviewDto);
    }

    @Override
    public ReviewDto getReviewDetail(int no) throws SQLException {
        return reviewMapper.getReviewDetail(no);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) throws SQLException {
        reviewMapper.updateReview(reviewDto);
    }

    @Override
    public void deleteReview(int no) throws SQLException {
        reviewMapper.deleteReview(no);
    }
}
