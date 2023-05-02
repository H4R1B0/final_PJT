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
	
	//키워드에 따라 관광지 검색 (전체 조회)
	@GetMapping("/search")
	public ResponseEntity<?> getListByKeyword(@RequestParam Map<String, String> param) throws SQLException {
		System.out.println(param);
		return ResponseEntity.ok().body(tourService.selectByKeyword(param));
	}

	//contentTypeId로 관광지 타입별 검색 (타입별로 조회)
	@GetMapping("/type")
	public ResponseEntity<?> getListByContentType(@RequestParam Map<String, String> param) throws SQLException {
		return ResponseEntity.ok().body(tourService.selectByContentTypeId(param));
	}


	//contentId로 관광지 검색 (상세 조회)
	@GetMapping("/{contentId}")
	public ResponseEntity<?> getDetailByContentId(@PathVariable int contentId) throws SQLException {
		System.out.println(contentId);
		return ResponseEntity.ok().body(tourService.selectByContentId(contentId));
	}


	//좋아요 증감
//	@PutMapping("/{contentId}/{memberId}")
//	public ResponseEntity<?> pushInterest(@PathVariable String contentId, @PathVariable String memberId) {
//
//	}

}
