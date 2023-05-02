package com.ssafy.traveler.tour.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.traveler.member.dto.MemberInterestDto;
import com.ssafy.traveler.tour.dto.TourInterestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.tour.model.mapper.TourMapper;

@Service
public class TourServiceImpl implements TourService {

	@Autowired
	private TourMapper tourMapper;

	//제목 or 주소 포함 조회
	@Override
	public List<TourDto> selectByKeyword(Map<String, String> param) throws SQLException {
		return tourMapper.selectByKeyword(param);
	}

	//contentTypeId로 관광지 타입별 조회
	@Override
	public List<TourDto> selectByContentTypeId(Map<String, String> param) throws SQLException {
		return tourMapper.selectByContentTypeId(param);
	}

	//contentId로 상세 조회
	@Override
	public TourDto selectByContentId(int contentId) throws SQLException {
		return tourMapper.selectByContentId(contentId);
	}

	//사용자가 좋아요 눌렀는지 확인
	@Override
	public int isInterestInMember(MemberInterestDto memberInterest) throws SQLException {
		return tourMapper.isInterestInMember(memberInterest);
	}

	//한 번 이라도 좋아요를 받은 관광지인지 확인
	@Override
	public int isInterestInAttraction(int contentId) throws SQLException {
		return tourMapper.isInterestInAttraction(contentId);
	}

	//처음이라면 관광지 추가
	@Override
	public void insertNewAttractionInterest(int contentId) throws SQLException {
		tourMapper.insertNewAttractionInterest(contentId);
	}

	//처음이 아니라면 좋아요 수 증가
	@Override
	public void updateInterestCount(int contentId) throws SQLException {
		tourMapper.updateInterestCount(contentId);
	}

	//사용자의 좋아요 목록에 추가
	@Override
	public void insertMemberInterest(MemberInterestDto memberInterest) throws SQLException {
		tourMapper.insertMemberInterest(memberInterest);
	}

	//좋아요 증감
	public void upDownInterest(MemberInterestDto memberInterest) throws SQLException {
		
		//사용자가 좋아요를 누르지 않은 경우
		if(isInterestInMember(memberInterest) == 0) {
			//처음 좋아요를 받은 관광지인 경우
			if(isInterestInAttraction(memberInterest.getContentId()) == 0) {
				insertNewAttractionInterest(memberInterest.getContentId());
			}
			else {
				updateInterestCount(memberInterest.getContentId());
			}
			insertMemberInterest(memberInterest);
		}
	}




}
