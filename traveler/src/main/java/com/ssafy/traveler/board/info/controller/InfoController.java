package com.ssafy.traveler.board.info.controller;

import com.ssafy.traveler.board.info.dto.InfoDto;
import com.ssafy.traveler.board.info.model.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<InfoDto> infoList = infoService.selectAll();
        return ResponseEntity.ok().body(infoList);
    }
}
