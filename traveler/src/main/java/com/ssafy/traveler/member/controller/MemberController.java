package com.ssafy.traveler.member.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.traveler.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.traveler.member.dto.MemberDto;
import com.ssafy.traveler.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Slf4j
@CrossOrigin("*")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 로그인
     *
     * @param data
     * @return
     * @throws SQLException
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) throws SQLException, UnsupportedEncodingException {
        log.debug(data.get("member_id") + " " + data.get("member_password"));
        MemberDto member = memberService.login(data);

        //로그인 성공
        if (member != null) {
            String token = jwtUtil.createToken(member, 60 * 60);    //60초 * 60분
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
        }

        //로그인 실패
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 회원가입
     *
     * @param data
     * @return
     * @throws SQLException
     */
    @PostMapping
    public ResponseEntity<?> join(@RequestBody Map<String, String> data) throws SQLException {
        log.debug(data.get("member_id") + " " + data.get("member_password"));
        memberService.join(data);
        return ResponseEntity.ok().build();
    }

    /**
     * 아이디 중복 확인
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    @GetMapping("/{member_id}")
    public ResponseEntity<?> idCheck(@PathVariable("member_id") String memberId) throws SQLException {
        return new ResponseEntity<Integer>(memberService.idCheck(memberId), HttpStatus.OK);
    }

    /**
     * 로그아웃
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<?> logout() {
        log.debug("로그아웃");
        return ResponseEntity.ok().build();
    }

    /**
     * 마이페이지
     */
    @GetMapping("/profile/{memberId}")
    public ResponseEntity<?> getMemberDto(@PathVariable String memberId) throws SQLException {
        log.debug("마이페이지");
        return ResponseEntity.ok(memberService.getMemberDto(memberId));
    }


    /**
     * 회원 탈퇴
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> withdrawal(@PathVariable String memberId) throws SQLException {
        log.debug("회원 탈퇴");
        memberService.withdrawal(memberId);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 정보 수정
     *
     * @param data
     * @return
     */
    @PutMapping
    public ResponseEntity<?> modify(@RequestBody Map<String, String> data) throws SQLException {
        log.debug("회원 정보 수정");
        memberService.modify(data);
        return ResponseEntity.ok().build();
    }

    /**
     * 좋아요한 관광지 가져오기
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    @GetMapping("/interest/{memberId}")
    public ResponseEntity<?> getInterestList(@PathVariable String memberId) throws SQLException {
        log.debug("좋아요한 관광지 가져오기");
        return ResponseEntity.ok(memberService.getInterestList(memberId));
    }

}
