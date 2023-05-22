package com.ssafy.traveler.board.review.model.service;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import com.ssafy.traveler.board.review.model.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> selectAllReviews(Map<String, String> param) throws SQLException {
        return reviewMapper.selectAllReviews(param);
    }

    @Override
    public int getTotalCount() throws SQLException {
        return reviewMapper.getTotalCount();
    }

    @Override
    public void writeReview(ReviewDto reviewDto) throws SQLException {
        reviewMapper.writeReview(reviewDto);
    }

    @Override
    public ReviewDto getReviewDetail(int no) throws SQLException {
        reviewMapper.hit(no);
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
