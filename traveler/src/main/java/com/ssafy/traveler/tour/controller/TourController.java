package com.ssafy.traveler.tour.controller;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.traveler.member.dto.MemberInterestDto;
import com.ssafy.traveler.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.tour.model.service.TourService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/tour")
@Slf4j
@CrossOrigin("*")
public class TourController {
    final int LIMIT = 10; //LIMIT만큼 불러옴

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TourService tourService;

    //키워드에 따라 관광지 검색 (전체 조회)
    @GetMapping("/search")
    public ResponseEntity<?> getListByKeyword(@RequestParam Map<String, String> param, HttpServletRequest request) throws SQLException, JsonProcessingException {
        //좋아요를 눌었는지 확인 하기 위한 토큰 가져오기
        final String token = request.getHeader("Authorization");
        log.debug("Authorization 헤더 : {}", token);
        //토큰 존재여부 체크
        if (token != null && token.startsWith("Bearer ")){
            String jwt = token.substring(7);
            log.debug("헤더에서 추출한 토큰 정보 : {}", jwt);
            if (jwtUtil.validateToken(jwt)) {
                log.debug("토큰 유효");
                String[] check = token.split("\\.");
                Base64.Decoder decoder = Base64.getDecoder();
                String payload = new String(decoder.decode(check[1]));

                ObjectMapper mapper = new ObjectMapper();
                Map returnMap = mapper.readValue(payload, Map.class);
                log.debug(returnMap.toString());
                param.put("member_id", (String) returnMap.get("member_id"));
            }
        }

        int page = Integer.parseInt(param.getOrDefault("page", "1"));
        page = (page - 1) * LIMIT;
        param.put("page", Integer.toString(page));
        param.put("LIMIT", Integer.toString(LIMIT));
        log.debug(param.toString());
        return ResponseEntity.ok().body(tourService.selectByKeyword(param));
    }

    //contentTypeId로 관광지 타입별 검색 (타입별로 조회)
//    @GetMapping("/type")
//    public ResponseEntity<?> getListByContentType(@RequestParam Map<String, String> param) throws SQLException {
//        int page = Integer.parseInt(param.getOrDefault("page", "1"));
//        page = (page - 1) * LIMIT;
//        param.put("page", Integer.toString(page));
//        param.put("LIMIT", Integer.toString(LIMIT));
//        log.debug(param.toString());
//        return ResponseEntity.ok().body(tourService.selectByContentTypeId(param));
//    }

    //contentTypeId로 관광지 타입별 검색 (타입별로 조회)
    @GetMapping("/total")
    public ResponseEntity<?> getTotalCount(@RequestParam Map<String, String> param) throws SQLException {
        log.debug(param.toString());
        return ResponseEntity.ok().body(tourService.getTotalCount(param));
    }


    //contentId로 관광지 검색 (상세 조회)
    @GetMapping("/{contentId}")
    public ResponseEntity<?> getDetailByContentId(@PathVariable int contentId) throws SQLException {
        System.out.println(contentId);
        return ResponseEntity.ok().body(tourService.selectByContentId(contentId));
    }


    //좋아요 증감
    @PutMapping("/{memberId}/{contentId}")
    public ResponseEntity<?> pushInterest(@PathVariable String memberId, @PathVariable int contentId) throws SQLException {
        MemberInterestDto memberInterest = new MemberInterestDto(memberId, contentId);
//        tourService.upDownInterest(memberInterest);
        return new ResponseEntity<Boolean>(tourService.upDownInterest(memberInterest), HttpStatus.OK);
//        return ResponseEntity.ok().build();
    }

}
