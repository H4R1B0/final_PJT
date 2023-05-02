package com.ssafy.traveler.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.traveler.member.dto.MemberDto;
import com.ssafy.traveler.member.model.mapper.MemberMapper;
import com.ssafy.traveler.util.SHA256Util;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDto login(Map<String, String> data) throws SQLException {
        String salt = getSalt(data.get("member_id"));
        String saltedPassword = SHA256Util.getEncrypt(data.get("member_password"), salt);
        data.put("member_password", saltedPassword);
        return memberMapper.login(data);
    }

    @Override
    public void join(Map<String, String> data) throws SQLException {
        String salt = SHA256Util.generateSalt();
        data.put("member_salt", salt);
        String saltedPassword = SHA256Util.getEncrypt(data.get("member_password"), salt);
        data.put("member_password", saltedPassword);
        memberMapper.join(data);
    }

    @Override
    public int idCheck(String memberId) throws SQLException {
        return memberMapper.idCheck(memberId);
    }

    @Override
    public void modify(@RequestBody Map<String, String> data) throws SQLException {
        memberMapper.modify(data);
    }

    @Override
    public void delete(String userId) throws SQLException {
        memberMapper.delete(userId);
    }

    @Override
    public String getSalt(String memberId) throws SQLException {
        return memberMapper.getSalt(memberId);
    }

    @Override
    public void withdrawal(String memberId) throws SQLException {
        memberMapper.withdrawal(memberId);
    }

    @Override
    public MemberDto getMemberDto(String memberId) throws SQLException {
        return memberMapper.getMemberDto(memberId);
    }

}
