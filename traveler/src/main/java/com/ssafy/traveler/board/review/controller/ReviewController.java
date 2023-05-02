package com.ssafy.traveler.board.review.controller;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import com.ssafy.traveler.board.review.model.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/board/review")
@Slf4j
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /**
     * 후기 목록 모두 가져오기
     *
     * @return List<ReviewDto>
     * @throws SQLException
     */
    @GetMapping
    public ResponseEntity<?> selectAllReviews() throws SQLException {
        log.debug("후기 목록 가져오기");

        return ResponseEntity.ok(reviewService.selectAllReviews());
    }

    /**
     * 후기 게시판 글쓰기
     *
     * @param reviewDto
     * @return
     * @throws SQLException
     */
    @PostMapping
    public ResponseEntity<?> writeReview(@RequestBody ReviewDto reviewDto) throws SQLException {
        log.debug("글 쓰기");
        log.debug(reviewDto.toString());
        reviewService.writeReview(reviewDto);
        return ResponseEntity.ok().build();
    }

    /**후기 게시판 상세 조회
     * @param no 상세 조회할 번호
     * @return
     * @throws SQLException
     */
    @GetMapping("/{no}")
    public ResponseEntity<?> getReviewDetail(@PathVariable int no) throws SQLException {
        log.debug("후기 게시판 상세 조회");
        return ResponseEntity.ok(reviewService.getReviewDetail(no));
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) throws SQLException {
        log.debug("후기 게시판 수정");
        reviewService.updateReview(reviewDto);
        return ResponseEntity.ok().build();
    }

}
