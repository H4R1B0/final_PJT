package com.ssafy.traveler.member.model.service;

import com.ssafy.traveler.member.dto.MemberDto;
import com.ssafy.traveler.member.model.mapper.MemberMapper;
import com.ssafy.traveler.tour.dto.TourDto;
import com.ssafy.traveler.util.SHA256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    final int SIZE = 10;

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
    public int modify(@RequestBody Map<String, String> data) throws SQLException {
        int changeCount = memberMapper.modify(data);
        if (changeCount == 1) {
            log.debug("개인 정보 변경됨");
        } else {
            log.debug("개인 정보 변경 오류");
        }
        return changeCount;
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

    @Override
    public int getInterestCount(Map<String, String> param) throws SQLException {
        return memberMapper.getInterestCount(param);
    }

    @Override
    public List<TourDto> getInterestList(Map<String, String> param) throws SQLException {
        return memberMapper.getInterestList(param);
    }

    @Override
    public int getAdminCount(String memberId) throws SQLException {
        return memberMapper.getAdminCount(memberId);
    }

    @Override
    public MemberDto getExistMember(String memberId) throws SQLException {
        return memberMapper.checkUserExist(memberId);
    }

    //임시 비밀번호 생성
    public String getRandPassword() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<SIZE; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }

    @Override
    public void updatePassword(MemberDto member) throws SQLException{
        Map<String, String> data = new HashMap<>();
        String salt = getSalt(member.getMemberId());
        String saltedPassword = SHA256Util.getEncrypt(member.getMemberPassword(), salt);
        data.put("member_id", member.getMemberId());
        data.put("member_password", saltedPassword);
        memberMapper.updatePassword(data);
    }

}
