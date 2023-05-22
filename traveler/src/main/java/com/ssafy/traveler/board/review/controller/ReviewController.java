package com.ssafy.traveler.board.review.controller;

import com.ssafy.traveler.board.review.dto.ReviewDto;
import com.ssafy.traveler.board.review.model.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/board/review")
@Slf4j
@CrossOrigin("*")
public class ReviewController {
    final int LIMIT = 10; //LIMIT만큼 불러옴
    @Autowired
    ReviewService reviewService;

    /**
     * 후기 목록 모두 가져오기
     *
     * @return List<ReviewDto>
     * @throws SQLException
     */
    @GetMapping(value = {"", "/total"})
    public ResponseEntity<?> selectAllReviews(@RequestParam Map<String, String> param, HttpServletRequest request) throws SQLException {
        log.debug("후기 목록 가져오기");
        if(request.getServletPath().equals("/board/review/total")){
            log.debug("후기 목록 총 개수");
            return ResponseEntity.ok(reviewService.getTotalCount());
        }
        int page = Integer.parseInt(param.getOrDefault("page", "1"));
        page = (page - 1) * LIMIT;
        param.put("page", Integer.toString(page));
        param.put("LIMIT", Integer.toString(LIMIT));
        return ResponseEntity.ok(reviewService.selectAllReviews(param));
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

    /**
     * 후기 게시판 상세 조회
     *
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

    @DeleteMapping("/{no}")
    public ResponseEntity<?> deleteReview(@PathVariable int no) throws SQLException {
        log.debug("후기 삭제");
        reviewService.deleteReview(no);
        return ResponseEntity.ok().build();
    }

}
