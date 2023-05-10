package com.ssafy.traveler.member.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 로그인
     *
     * @param data
     * @return
     * @throws SQLException
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) throws SQLException {
        log.debug(data.get("member_id") + " " + data.get("member_password"));
        MemberDto member = memberService.login(data);
        if (member == null) {
            log.debug("존재 안함");
        }
        log.debug(member.toString());
        return ResponseEntity.ok(member);
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
        return ResponseEntity.ok(memberService.idCheck(memberId));
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