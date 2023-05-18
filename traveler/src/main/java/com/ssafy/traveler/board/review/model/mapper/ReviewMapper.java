package com.ssafy.traveler.board.review.model.mapper;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewDto> selectAllReviews() throws SQLException;

    void writeReview(ReviewDto reviewDto) throws SQLException;

    ReviewDto getReviewDetail(int no) throws SQLException;

    void updateReview(ReviewDto reviewDto) throws SQLException;

    void deleteReview(int no) throws SQLException;

    void hit(int no) throws SQLException;
}
