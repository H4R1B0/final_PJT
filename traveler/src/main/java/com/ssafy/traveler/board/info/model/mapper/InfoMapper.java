package com.ssafy.traveler.board.info.model.mapper;

import com.ssafy.traveler.board.info.dto.InfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface InfoMapper {

    //전체 조회
    List<InfoDto> selectAll() throws SQLException;

    //상세 조회
    InfoDto selectByNo(int no) throws SQLException;
    
    //등록
    void insertInfoBoard(InfoDto infoDto) throws SQLException;
    
    //수정

    
    //삭제
    void deleteInfoBoard(int no) throws SQLException;

}
