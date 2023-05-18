package com.ssafy.traveler.board.info.model.mapper;

import com.ssafy.traveler.board.info.dto.InfoDto;
import com.ssafy.traveler.board.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface InfoMapper {

    //공지사항 전체 조회
    List<InfoDto> selectAll() throws SQLException;

    //공지사항 상세 조회
    InfoDto selectByNo(int no) throws SQLException;
    
    //공지사항 등록
    void insertInfoBoard(InfoDto infoDto) throws SQLException;
    
    //수정
    void updateInfoBoard(InfoDto infoDto) throws SQLException;
    
    //삭제
    void deleteInfoBoard(int no) throws SQLException;

    void hit(int no) throws SQLException;

}
