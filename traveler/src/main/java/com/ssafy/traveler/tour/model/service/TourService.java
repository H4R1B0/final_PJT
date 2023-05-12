package com.ssafy.traveler.tour.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.traveler.member.dto.MemberInterestDto;
import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.tour.dto.TourInterestDto;

public interface TourService {

    /**
     * 제목 or 주소 포함 조회
     *
     * @param param 검색할 키워드와 내용
     * @return 검색된 관광지 리스트
     * @throws SQLException
     */
    List<TourDto> selectByKeyword(Map<String, String> param) throws SQLException;

    /**
     * contentTypeId로 관광지 타입별 조회
     *
     * @param param 검색 키워드, 내용, 관광지 타입 코드
     * @return 검색된 관광지 타입별 리스트
     * @throws SQLException
     */
//    List<TourDto> selectByContentTypeId(Map<String, String> param) throws SQLException;

    /**
     * param 결과로 조회한 전체 개수 조회
     *
     * @param param
     * @return
     * @throws SQLException
     */
    int getTotalCount(Map<String, String> param) throws SQLException;

    /**
     * contentId로 상세 조회
     *
     * @param contentId
     * @return
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

    //좋아요 증감
    void upDownInterest(MemberInterestDto memberInterest) throws SQLException;

}
