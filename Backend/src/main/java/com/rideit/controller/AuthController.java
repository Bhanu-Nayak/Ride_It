//
//package com.rideit.controller;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.crypto.SecretKey;
//import javax.naming.AuthenticationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
////import java.net.http.HttpHeaders;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rideit.dto.LoginRequest;
//import com.rideit.dto.UserInfoResponse;
//import com.rideit.security.CustomUserDetails;
//import com.rideit.security.JwtUtils;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//	@Autowired
//	private JwtUtils jwtUtils;
//	@Value("${SECRET_KEY}")
//	private String jwtSecret;
//	@Value("${EXP_TIMEOUT}")
//	private int jwtExpirationMs;
//	@Autowired
//	private AuthenticationManager authMgr;
//	
//	
//		@PostMapping("/signin")
//		public ResponseEntity<?> singin(@RequestBody LoginRequest loginRequest) throws AuthenticationException{
//			Authentication authentication;
//	        authentication = authMgr
//			        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));;
//
//	            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//
//	        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//
//	        List<String> roles = userDetails.getAuthorities().stream()
//	                .map(item -> item.getAuthority())
//	                .collect(Collectors.toList());
//	        
//
//	        UserInfoResponse response = new UserInfoResponse(userDetails.getUser().getId(),userDetails.getUsername(),roles);
//
//	        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
//	                                    jwtCookie.toString())
//	                                    .body(response);
//
//		}
//		 @PostMapping("/login")
//		    public ResponseEntity<?> login(@RequestParam String email, HttpServletResponse response) {
//			 SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//		        String jwtToken = Jwts.builder()
//		                .setSubject(email)
//		                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) 
//		                .signWith(key)
//		                .compact();
//
//
//		        Cookie cookie = new Cookie("accessToken", jwtToken);
//		        cookie.setHttpOnly(true); 
//		        cookie.setSecure(false);  
//		        cookie.setPath("/");
//		        cookie.setMaxAge(24 * 60 * 60);
//		        response.addCookie(cookie);
//
//		        return ResponseEntity.ok(jwtToken);
//		    }
//		 
//		 
//		    @GetMapping("/protected")
//		    public ResponseEntity<String> getProtectedResource(@CookieValue(name = "accessToken", required = false) String token) {
//		    	SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//		        if (token == null) {
//		            return ResponseEntity.status(401).body("Unauthorized");
//		        }
//		        
//		        String username = Jwts.parserBuilder()
//		                .setSigningKey(key)
//		                .build()
//		                .parseClaimsJws(token)
//		                .getBody()
//		                .getSubject();
//
//		        return ResponseEntity.ok("Hello, " + username + "! You accessed a protected resource."+token);
//		    }
//	
//}
