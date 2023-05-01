package com.ssafy.traveler.tour.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.tour.model.service.TourService;

import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/tour")
@Slf4j
@CrossOrigin("*")
public class TourController {

	@Autowired
	private TourService tourService;
	
	//제목 포함 조회
//	@GetMapping
//	public ResponseEntity<?> getListByTitle(@RequestParam("title") String searchTitle) throws SQLException {
//		
//		List<TourDto> tourList = tourService.selectByTitle(searchTitle);
//		log.debug("tourList", tourList);
//		return new ResponseEntity<>(tourList, HttpStatus.OK);
//	}

	//키워드에 따라 관광지 검색
	@GetMapping
	public ResponseEntity<?> getListByKeyword(@RequestParam Map<String, String> param) throws SQLException {

		System.out.println(param);
		return ResponseEntity.ok().body(tourService.selectByKeyword(param));
	}

	//contentId로 관광지 검색 (상세 조회)
	@GetMapping("/{contentId}")
	public ResponseEntity<?> getDetailByContentId(@PathVariable String contentId) throws SQLException {
		System.out.println(contentId);
		return ResponseEntity.ok().body(tourService.selectByContentId(contentId));
	}

	//좋아요 증감
//	@PutMapping("/{contentId}/{memberId}")
//	public ResponseEntity<?> pushInterest(@PathVariable String contentId, @PathVariable String memberId) {
//
//	}

}
