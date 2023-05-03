package com.ssafy.traveler.board.info.model.service;

import com.ssafy.traveler.board.info.dto.InfoDto;
import com.ssafy.traveler.board.info.model.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService{

    @Autowired
    private InfoMapper infoMapper;

    //공지사항 전체 조회
    @Override
    public List<InfoDto> selectAll() throws SQLException {
        return infoMapper.selectAll();
    }

    //공지사항 상세 조회
    @Override
    public InfoDto selectByNo(int no) throws SQLException {
        return infoMapper.selectByNo(no);
    }

    //공지사항 등록
    @Override
    public void insertInfoBoard(InfoDto infoDto) throws SQLException {
        infoMapper.insertInfoBoard(infoDto);
    }

    //공지사항 수정
    @Override
    public void updateInfoBoard(InfoDto infoDto) throws SQLException {
        infoMapper.updateInfoBoard(infoDto);
    }

    //공지사항 삭제
    @Override
    public void deleteInfoBoard(int no) throws SQLException {
        infoMapper.deleteInfoBoard(no);
    }
}
