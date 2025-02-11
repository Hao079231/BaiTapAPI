package vn.itz.jpastudying.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.model.Student;

@Slf4j
@Service
public class JwtService {
  @Value("${jwt.signerKey}")
  @NonFinal
  protected String SIGN_KEY;

  public String extractUsername(String token){
    return extractClaim(token, Claims::getSubject);
  }

  public String extractRole(String token) {
    return extractClaim(token, claims -> claims.get("role", String.class));
  }

  public boolean isValid(String token, UserDetails student){
    String username = extractUsername(token);
    return (username.equals(student.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> resolvers){
    Claims claims = extractAllClaims(token);
    return resolvers.apply(claims);
  }

  private Claims extractAllClaims(String token){
    return Jwts.parserBuilder()
        .setSigningKey(getSigninKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  // Tao token chua username, role, danh sach cac quyen
  public String generateToken(Student student){
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", student.getRole().name());
    claims.put("authorities", student.getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(student.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(getSigninKey())
        .compact();
  }

  public List<String> extractAuthorities(String token) {
    return extractClaim(token, claims -> claims.get("authorities", List.class));
  }

  private SecretKey getSigninKey(){
    byte[] keyBytes = Decoders.BASE64URL.decode(SIGN_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
