package com.ssafy.traveler.member.controller;

import com.ssafy.traveler.member.dto.MemberDto;
import com.ssafy.traveler.member.model.service.MemberService;
import com.ssafy.traveler.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor //오류 있으면 지우기, 근데 얘 지워도 오류
public class MemberController {

    //메일 전송
    private final JavaMailSender javaMailSender;

    final int LIMIT = 10; //LIMIT만큼 불러옴
    @Autowired
    MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    //발신자 메일 주소
    @Value("${spring.mail.username}")
    private String from;

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
     * memberId가 admin 권한을 가지고 있는지 확인
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    @GetMapping("/admin/{memberId}")
    public ResponseEntity<?> getAdminCount(@PathVariable String memberId) throws SQLException {
        log.debug("admin 확인");
        return ResponseEntity.ok().body(memberService.getAdminCount(memberId));
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
     * 토큰에 있는 개인 정보 수정해서 재발급
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

    /**
     * 존재하는 회원인지 확인하고 임시 비밀번호 발급
     */
    @PostMapping("/tmpPwd/{memberId}")
    public ResponseEntity<?> getTmpPassword(@PathVariable String memberId) throws SQLException, MessagingException {
        MemberDto member = memberService.getExistMember(memberId);
        if(member.getEmail() != null) {
            String password = memberService.getRandPassword();
            member.setMemberPassword(password);
            memberService.updatePassword(member);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(member.getEmail());
            mimeMessageHelper.setSubject("[Traveler] 임시 비밀번호 안내");

            String msg = "<div style='border: 1px solid black; padding: 10px; font-family: verdana;'>";
            msg += "<h2>안녕하세요. <span style='color: blue;'>" + member.getMemberName() + "</span>님.</h2>";
            msg += "<p>초기화된 비밀번호를 전송해 드립니다. 비밀번호를 변경하여 사용하세요.</p>";
            msg += "<p>임시 비밀번호 : <span style='color: blue;'>" + password + "</span></p></div>";

            mimeMessageHelper.setText(msg, true);
            //사진 추가하고 싶으면 사진 추가
            javaMailSender.send(mimeMessage);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
