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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
@Slf4j
@CrossOrigin("*")
public class MemberController {
    final int LIMIT = 10; //LIMIT만큼 불러옴
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
    public ResponseEntity<?> modify(@RequestBody Map<String, String> data) throws SQLException, UnsupportedEncodingException {
        log.debug("회원 정보 수정");
        //변경된 행 수
        int modifyResult = memberService.modify(data);
        //변경 완료
        if (modifyResult == 1) {
            MemberDto member = new MemberDto();
            member.setMemberName(data.get("member_name"));
            member.setMemberId(data.get("member_id"));
            member.setEmail(data.get("email"));
            String token = jwtUtil.createToken(member, 60 * 60);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
        }
        //수정 실패
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 좋아요한 관광지 가져오기
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    @GetMapping(value = {"/interest", "/interest.total"})
    public ResponseEntity<?> getInterestList(@RequestParam Map<String, String> param, HttpServletRequest request) throws SQLException {
        log.debug(request.getServletPath());
        //사용자가 좋아요한 총 개수 가져오기
        if (request.getServletPath().equals("/member/interest.total")) {
            log.debug("좋아요한 관광지 총 개수");
            return ResponseEntity.ok(memberService.getInterestCount(param));
        }

        log.debug("좋아요한 관광지 가져오기");
        //사용자가 좋아요한 관광지들 LIMIT만큼 가져오기
        int page = Integer.parseInt(param.getOrDefault("page", "1"));
        page = (page - 1) * LIMIT;
        param.put("page", Integer.toString(page));
        param.put("LIMIT", Integer.toString(LIMIT));
        return ResponseEntity.ok(memberService.getInterestList(param));
    }

}
