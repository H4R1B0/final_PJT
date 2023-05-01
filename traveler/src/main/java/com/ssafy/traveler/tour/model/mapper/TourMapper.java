package com.ssafy.traveler.tour.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.traveler.tour.dto.TourDto;

@Mapper
public interface TourMapper {

	/**
	 * 제목 or 주소 포함 조회
	 * @param  param 검색할 키워드와 내용
	 * @return 검색된 관광지 리스트
	 * @throws SQLException
	 */
	List<TourDto> selectByKeyword(Map<String, String> param) throws SQLException;

	/**
	 * contentId로 조회 (상세 조회)
	 * @param contentId
	 * @return
	 * @throws SQLException
	 */
	TourDto selectByContentId(String contentId) throws SQLException;


}
