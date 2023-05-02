package com.ssafy.traveler.tour.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.traveler.member.dto.MemberInterestDto;
import com.ssafy.traveler.tour.dto.TourInterestDto;
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

	//사용자가 좋아요 눌렀는지 확인
	int isInterestInMember(MemberInterestDto memberInterest) throws SQLException;

	//사용자가 누르지 않았다면 attraction-interest에서 최초인 경우 확인
	int isInterestInAttraction(int contentId) throws SQLException;

	//최초인 경우 초기값 추가
	void insertNewAttractionInterest(int contentId) throws SQLException;

	//최초가 아닌 경우 좋아요 카운트 증가
	void addInterestCount(int contentId) throws SQLException;

	//member_interest에 추가
	void insertMemberInterest(MemberInterestDto memberInterest) throws SQLException;

	//사용자가 눌렀다면 attraction_interest에서 좋아요 카운트 감소
	void subInterestCount(int contentId) throws SQLException;

	//사용자가 눌렀다면 member-interest에서 데이터 삭제
	void deleteMemberInterest(MemberInterestDto memberInterest) throws SQLException;

}
