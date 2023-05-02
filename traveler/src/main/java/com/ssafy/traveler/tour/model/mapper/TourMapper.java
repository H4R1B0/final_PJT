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
	 * contentTypeId로 관광지 타입별 조회
	 * @param param 검색 된 내용에서 타입별 조회
	 * @param contentTypeId
	 * @return
	 * @throws SQLException
	 */
	List<TourDto> selectByContentTypeId(Map<String, String> param) throws SQLException;

	/**
	 * contentId로 상세 조회
	 * @param contentId pk인 contentId
	 * @return 조회 결과 TourDto 타입 한 개 반환
	 * @throws SQLException
	 */
	TourDto selectByContentId(int contentId) throws SQLException;


}
