package com.ssafy.traveler.board.info.controller;

import com.ssafy.traveler.board.info.dto.InfoDto;
import com.ssafy.traveler.board.info.model.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/board/info")
@Slf4j
@CrossOrigin("*")
public class InfoController {

    @Autowired
    private InfoService infoService;

    //전체 조회
    @GetMapping
    public ResponseEntity<?> getInfoList() throws SQLException {
        log.debug("공지사항 전체 조회");
        return ResponseEntity.ok(infoService.selectAll());
    }

    //상세 조회
    @GetMapping("/{no}")
    public ResponseEntity<?> getInfoDetail(@PathVariable int no) throws SQLException {
        log.debug("공지사항 상세 조회");
        return ResponseEntity.ok(infoService.selectByNo(no));
    }

    //등록
    @PostMapping
    public ResponseEntity<?> registInfo(@RequestBody InfoDto infoDto) throws SQLException {
        log.debug("공지사항 등록");
        infoService.insertInfoBoard(infoDto);
        return ResponseEntity.ok().build();
    }

    //수정
    @PutMapping
    public ResponseEntity<?> updateInfo(@RequestBody InfoDto infoDto) throws SQLException {
        log.debug("공지사항 수정");
        infoService.updateInfoBoard(infoDto);
        return ResponseEntity.ok().build();
    }

    //삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<?> deleteInfo(@PathVariable int no) throws SQLException {
        log.debug("공지사항 삭제");
        infoService.deleteInfoBoard(no);
        return ResponseEntity.ok().build();
    }

}
