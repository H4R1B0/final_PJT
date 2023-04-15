package com.traveler.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.traveler.dto.AttractionInfo;
import com.traveler.util.DBUtil;

public class AttractionInfoDAO {

	//싱글톤
	private AttractionInfoDAO() {}
	private static AttractionInfoDAO instance = new AttractionInfoDAO();
	public static AttractionInfoDAO getInstance()  {
		return instance;
	}
	
	//dbUtil
	private DBUtil dbUtil = DBUtil.getInstance();
	
	//전체 조회
	public List<AttractionInfo> selectAll() throws SQLException {
		
		System.out.println("전체 조회 dao");
		
		String sql = "select content_id, title, addr1, first_image, first_image2 from attraction_info";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<AttractionInfo> attractionList = new ArrayList<>();
			while(rs.next()) {
				
				String content_id = rs.getString("content_id");
				String title = rs.getString("title");
				String addr1 = rs.getString("addr1");
				String first_image = rs.getString("first_image");
				String first_image2 = rs.getString("first_image2");
				
				AttractionInfo attraction = new AttractionInfo(content_id, title, addr1, first_image, first_image2);
				attractionList.add(attraction);
			}
			return attractionList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	//title 포함 조회
	public List<AttractionInfo> selectByTitle(String searchTitle) throws SQLException {
		
		System.out.println("title 포함 조회 dao");
		
		String sql = "select content_id, title, addr1, first_image, first_image2 from attraction_info where title like ?;";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTitle+"%");
			
			rs = pstmt.executeQuery();
			
			List<AttractionInfo> attractionList = new ArrayList<>();
			while(rs.next()) {
				
				String content_id = rs.getString("content_id");
				String title = rs.getString("title");
				String addr1 = rs.getString("addr1");
				String first_image = rs.getString("first_image");
				String first_image2 = rs.getString("first_image2");
				
				AttractionInfo attraction = new AttractionInfo(content_id, title, addr1, first_image, first_image2);
				attractionList.add(attraction);
			}
			return attractionList;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
	}
	
	//content_id로 조회
	public AttractionInfo selectByContentID(String content_id) throws SQLException {
		
		System.out.println("content_id로 조회 dao");
		
		String sql = "select content_id, title, addr1, first_image, first_image2 from attraction_info where content_id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				AttractionInfo attractionInfo = new AttractionInfo();
				
				attractionInfo.setcontent_id(rs.getString("content_id"));
				attractionInfo.setTitle(rs.getString("title"));
				attractionInfo.setAddr(rs.getString("addr1"));
				attractionInfo.setImg1(rs.getString("first_image"));
				attractionInfo.setImg2(rs.getString("first_image2"));
				
				return attractionInfo;
					
				}
			
			return null;
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public AttractionInfo selectlocationById(String content_id) throws SQLException {
		
		System.out.println("위도, 경도 조회 DAO");
		
		String sql = "select content_id, title, addr1, latitude, longitude from attraction_info where content_id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				AttractionInfo attractionInfo = new AttractionInfo();
				
				attractionInfo.setcontent_id(rs.getString("content_id"));
				attractionInfo.setTitle(rs.getString("title"));
				attractionInfo.setAddr(rs.getString("addr1"));
				attractionInfo.setLatitude(rs.getDouble("latitude"));
				attractionInfo.setLongitude(rs.getDouble("longitude"));
				
				return attractionInfo;
					
				}
			
			return null;
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		AttractionInfoDAO attractionDao = new AttractionInfoDAO();
		
//		List<AttractionInfo> list = attractionDao.selectAll();
//		System.out.println(list);
		
//		List<AttractionInfo> listTitle = attractionDao.selectByTitle("휴양림");
//		System.out.println(listTitle);
		
//		AttractionInfo att = attractionDao.selectByContentID("125266");
//		System.out.println(att);
		
		AttractionInfo att = attractionDao.selectlocationById("126499");
		System.out.println(att);
	}
	
}
