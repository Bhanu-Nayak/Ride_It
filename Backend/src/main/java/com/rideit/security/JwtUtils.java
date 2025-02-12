package com.rideit.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}")
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	@Value("${spring.ecom.app.jwtCookieName}")
	private String jwtCookie;
	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}


	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				.signWith(key, SignatureAlgorithm.HS512).compact();
		}


	public String getUserEmailFromJwtToken(Claims claims) {
		return claims.getSubject();
	}


	public Claims validateJwtToken(String jwtToken) {

		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();

		return claims;		
	}

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream()
				.map(authority ->authority.getAuthority()) 
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
		public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		return authorities;
	}

			


	    public ResponseCookie generateJwtCookie(CustomUserDetails userPrincipal) {
	        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
	        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt)
	                .path("/api")
	                .maxAge(24 * 60 * 60)
	                .httpOnly(false)
	                .build();
	        return cookie;
	    }

	    public String generateTokenFromUsername(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	                .signWith(key)
	                .compact();
	    }
			
	public Authentication populateAuthenticationTokenFromJWT(String jwt) {
			Claims payloadClaims = validateJwtToken(jwt);
			String email = getUserEmailFromJwtToken(payloadClaims);
			List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,"",
						authorities);
			System.out.println("is authenticated "+token.isAuthenticated());//true
			return token;
		
	}

}
