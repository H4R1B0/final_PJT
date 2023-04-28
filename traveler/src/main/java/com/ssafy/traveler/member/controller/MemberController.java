package com.ssafy.traveler.member.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> data) throws SQLException {
		log.debug(data.get("member_id") + " " + data.get("member_password"));
		MemberDto member = memberService.login(data);
		if (member == null)
			log.debug("존재 안함");
		return ResponseEntity.ok(member);
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody Map<String, String> data) throws SQLException {
		log.debug(data.get("member_id") + " " + data.get("member_password"));
		memberService.join(data);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{member_id}")
	public ResponseEntity<?> idCheck(@PathVariable("member_id") String memberId) throws SQLException {
		return ResponseEntity.ok(memberService.idCheck(memberId));
	}
}
