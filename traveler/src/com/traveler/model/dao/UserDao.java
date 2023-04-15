package com.traveler.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.traveler.dto.User;
import com.traveler.util.DBUtil;

public class UserDao {
	private DBUtil dbUtil = DBUtil.getInstance();
	private static UserDao userDao = new UserDao();

	private UserDao() {}
	
	static public UserDao getInstance() {
		return userDao;
	}
	
	public String login(User user) throws SQLException {
		String sql="select * from user where id=? and pwd=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return user.getId();
			}
			else {
				return null;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public boolean signin(User user) throws SQLException {
		String sql="insert into user values (?, ?, ?, ?);";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int change = 0;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getEmail());
			pstmt.setBoolean(4, user.isAdvertisement());
			change = pstmt.executeUpdate();
			
			if(change == 1) {
				return true;
			}
			else {
				return false;
			}
		} finally {
			dbUtil.close( pstmt, conn);
		}
	}
	
	public User check(User user) throws SQLException {
		String sql="select * from user where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user.setEmail(rs.getString("email"));
				user.setPwd(rs.getString("pwd"));
				user.setAdvertisement(rs.getBoolean("advertisement"));
				return user;
			}
			else {
				return null;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public boolean update(User user) throws SQLException {
		String sql="UPDATE user SET email=?, pwd=?, advertisement=? WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int check = 0;
		
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPwd());
			pstmt.setBoolean(3, user.isAdvertisement());
			pstmt.setString(4, user.getId());
			check = pstmt.executeUpdate();
			
			if(check == 1) {
				return true;
			}
			else {
				return false;
			}
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	public boolean delete(User user) throws SQLException {
		String sql="delete from user where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int i= 0;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			i = pstmt.executeUpdate();
			
			if( i == 1) {
				return true;
			}
			else {
				return false;
			}
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
