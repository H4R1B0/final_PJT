package com.traveler.model.service;

import java.sql.SQLException;
import java.util.List;

import com.traveler.dto.AttractionInfo;
import com.traveler.model.dao.AttractionInfoDAO;

public class AttractionInfoService {

	private AttractionInfoService() {}
	private static AttractionInfoService instance = new AttractionInfoService();
	public static AttractionInfoService getInstance() {
		return instance;
	}
	
	//dao 가져오기
	private AttractionInfoDAO attractionInfoDao = AttractionInfoDAO.getInstance();
	
	public List<AttractionInfo> selectAll() throws SQLException {
		return attractionInfoDao.selectAll();
	}
	
	public List<AttractionInfo> selectByTitle(String searchTitle) throws SQLException {
		return attractionInfoDao.selectByTitle(searchTitle);
	}
	
	public AttractionInfo selectByContentID(String content_id) throws SQLException {
		return attractionInfoDao.selectByContentID(content_id);
	}
	
	public AttractionInfo selectlocationById(String content_id) throws SQLException {
		return attractionInfoDao.selectlocationById(content_id);
	}
	
}
