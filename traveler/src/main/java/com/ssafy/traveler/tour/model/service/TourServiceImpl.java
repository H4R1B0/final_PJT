package com.ssafy.traveler.tour.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.tour.model.mapper.TourMapper;

@Service
public class TourServiceImpl implements TourService {

	@Autowired
	private TourMapper tourMapper;
	
	@Override
	public List<TourDto> selectByKeyword(Map<String, String> param) throws SQLException {
		return tourMapper.selectByKeyword(param);
	}

	@Override
	public TourDto selectByContentId(String contentId) throws SQLException {
		return tourMapper.selectByContentId(contentId);
	}

}
