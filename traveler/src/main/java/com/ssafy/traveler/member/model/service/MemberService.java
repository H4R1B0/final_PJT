package com.ssafy.traveler.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.traveler.member.dto.MemberDto;

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
     * @param member
     * @throws SQLException
     */
    void modify(MemberDto member) throws SQLException;

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
    abstract String getSalt(String memberId) throws SQLException;

    /**
     * 회원 탈퇴
     *
     * @param memberId
     * @throws SQLException
     */

    abstract void withdrawal(String memberId) throws SQLException;

    abstract MemberDto getMemberDto(String memberId) throws SQLException;
}
