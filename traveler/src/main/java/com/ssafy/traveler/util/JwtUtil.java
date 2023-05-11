package com.ssafy.traveler.util;

import com.ssafy.traveler.member.dto.MemberDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
	private final String SECRET_KEY = "ssafy";

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(token);
			log.debug("토큰 존재하고 유효하므로 요청 정상 처리");
			return true;

		} catch (Exception e) {
			log.debug("토큰은 존재하나 유효하지 않음.\n 에러내용 : {}", e.getMessage());
			return false;
		}
}
	public String createToken(MemberDto memberDto, int expirationSecond) throws UnsupportedEncodingException {

		String token = Jwts.builder()
				//header 
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("alg","HS256")
				//payload
				.claim("id", memberDto.getMemberId())
				.claim("name", memberDto.getMemberName())
				.claim("email", memberDto.getEmail())
				.setExpiration( new Date(System.currentTimeMillis() + 1000 * expirationSecond) )
				//singature
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
				.compact();
		log.debug("발급된 토큰 : {}", token);
		return token;
	}

}
