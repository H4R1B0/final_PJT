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

    @Override
    public List<InfoDto> selectAll() throws SQLException {
        return infoMapper.selectAll();
    }

    @Override
    public InfoDto selectByNo(int no) throws SQLException {
        return infoMapper.selectByNo(no);
    }

    @Override
    public void insertInfoBoard(InfoDto infoDto) throws SQLException {
        infoMapper.insertInfoBoard(infoDto);
    }

    @Override
    public void deleteInfoBoard(int no) throws SQLException {
        infoMapper.deleteInfoBoard(no);
    }
}
