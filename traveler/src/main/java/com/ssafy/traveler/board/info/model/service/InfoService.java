package com.ssafy.traveler.board.info.model.service;

import com.ssafy.traveler.board.info.dto.InfoDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface InfoService {

    //공지사항 전체 조회
    List<InfoDto> selectAll(Map<String, String> param) throws SQLException;

    //공지사항 전체 개수
    int getTotalCount() throws SQLException;

    //공지사항 상세 조회
    InfoDto selectByNo(int no) throws SQLException;

    //공지사항 등록
    void insertInfoBoard(InfoDto infoDto) throws SQLException;

    //공지사항 수정
    void updateInfoBoard(InfoDto infoDto) throws SQLException;

    //공지사항 삭제
    void deleteInfoBoard(int no) throws SQLException;

}
