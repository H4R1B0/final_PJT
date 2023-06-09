package com.ssafy.traveler.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.traveler.member.dto.MemberDto;
import com.ssafy.traveler.tour.dto.TourDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.RequestBody;

public interface MemberService {
    /**
     * 로그인
     *
     * @param map 입력 정보가 저장된 map
     * @return member의 정보
     * @throws SQLException
     */
    abstract MemberDto login(Map<String, String> data) throws SQLException;

    /**
     * 회원가입
     *
     * @param member member의 정보
     * @throws SQLException
     */
    abstract void join(Map<String, String> data) throws SQLException;

    /**
     * 아이디 체크
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    abstract int idCheck(String memberId) throws SQLException;

    /**
     * 회원 정보 수정
     *
     * @param data
     * @throws SQLException
     */
    int modify(@RequestBody Map<String, String> data) throws SQLException;

    /**
     * 회원 탈퇴
     *
     * @param memberId
     * @throws SQLException
     */
    void delete(String memberId) throws SQLException;

    /**
     * 사용자의 salt값 가져오기
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    abstract String getSalt(String memberId) throws SQLException;

    /**
     * 회원 탈퇴
     *
     * @param memberId
     * @throws SQLException
     */

    abstract void withdrawal(String memberId) throws SQLException;

    abstract MemberDto getMemberDto(String memberId) throws SQLException;

    abstract int getInterestCount(Map<String, String> param) throws SQLException;

    List<TourDto> getInterestList(Map<String, String> param) throws SQLException;

    int getAdminCount(String memberId) throws SQLException;

    /**
     * 비밀번호 찾으려는 회원이 존재하는 회원인지 확인
     */
    MemberDto getExistMember(String memberId) throws SQLException;

    //랜덤 비밀번호 생성
    String getRandPassword();

    void updatePassword(MemberDto member) throws SQLException;
}
