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
//	@Override
//	public List<TourDto> selectByContentTypeId(Map<String, String> param) throws SQLException {
//		return tourMapper.selectByContentTypeId(param);
//	}

	@Override
	public int getTotalCount(Map<String, String> param) throws SQLException {
		return tourMapper.getTotalCount(param);
	}

	//contentId로 상세 조회
	@Override
	public TourDto selectByContentId(int contentId) throws SQLException {
		tourMapper.hit(contentId);
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
	public void addInterestCount(int contentId) throws SQLException {
		tourMapper.addInterestCount(contentId);
	}

	//사용자가 누르지 않았다면 사용자의 좋아요 목록에 추가
	@Override
	public void insertMemberInterest(MemberInterestDto memberInterest) throws SQLException {
		tourMapper.insertMemberInterest(memberInterest);
	}

	//사용자가 눌렀다면 attraction_interest에서 좋아요 카운트 감소
	@Override
	public void subInterestCount(int contentId) throws SQLException {
		tourMapper.subInterestCount(contentId);
	}

	//사용자가 눌렀다면 member-interest에서 데이터 삭제
	@Override
	public void deleteMemberInterest(MemberInterestDto memberInterest) throws SQLException {
		tourMapper.deleteMemberInterest(memberInterest);
	}

	//좋아요 증감
	public boolean upDownInterest(MemberInterestDto memberInterest) throws SQLException {
		
		//사용자가 좋아요를 누르지 않은 경우
		if(isInterestInMember(memberInterest) == 0) {
			//처음 좋아요를 받은 관광지인 경우
			if(isInterestInAttraction(memberInterest.getContentId()) == 0) {
				insertNewAttractionInterest(memberInterest.getContentId());
			}
			//이미 좋아요를 받은 관광지인 경우
			else {
				addInterestCount(memberInterest.getContentId());
			}
			insertMemberInterest(memberInterest);
			return true;
		}
		//사용자가 좋아요를 이미 누른 경우
		else {
			subInterestCount(memberInterest.getContentId());
			deleteMemberInterest(memberInterest);
			return false;
		}
	}

	//메인화면 슬라이더에 들어간 랜덤 관광지
	public List<TourDto> selectByRandom() throws SQLException{
		return tourMapper.selectByRandom();
	}

}
