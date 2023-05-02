package com.ssafy.traveler.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.traveler.tour.dto.TourDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.traveler.member.dto.MemberDto;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface MemberMapper {

    /**
     * 로그인
     *
     * @param map 입력 정보가 저장된 map
     * @return member의 정보
     * @throws SQLException
     */
    MemberDto login(Map<String, String> data) throws SQLException;

    /**
     * 회원가입
     *
     * @param data member의 정보
     * @throws SQLException
     */
    void join(Map<String, String> data) throws SQLException;

    /**
     * 아이디로 마이페이지 정보 가져오기
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    MemberDto getMemberDto(String memberId) throws SQLException;

    /**
     * 아이디 체크
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    int idCheck(String memberId) throws SQLException;

    /**
     * 회원 정보 수정
     *
     * @param member
     * @throws SQLException
     */
    void modify(@RequestBody Map<String, String> data) throws SQLException;

    /**
     * 회원 탈퇴
     *
     * @param userId
     * @throws SQLException
     */
    void delete(String userId) throws SQLException;

    /**
     * 사용자의 salt값 가져오기
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    String getSalt(String memberId) throws SQLException;

    /**
     * 회원 탈퇴
     *
     * @param memberId
     * @throws SQLException
     */

    void withdrawal(String memberId) throws SQLException;

    List<TourDto> getInterestList(String memberId) throws SQLException;
}
