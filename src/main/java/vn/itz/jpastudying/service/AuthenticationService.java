package vn.itz.jpastudying.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.Dto.IntrospectDto;
import vn.itz.jpastudying.Dto.StudentDto;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.exceptions.UnauthorizedException;
import vn.itz.jpastudying.form.authentication.AuthenticationForm;
import vn.itz.jpastudying.form.authentication.IntrospectForm;
import vn.itz.jpastudying.mapper.StudentMapper;
import vn.itz.jpastudying.repository.StudentRepository;

@Slf4j
@Service
public class AuthenticationService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentMapper studentMapper;

  @Value("${jwt.signerKey}")
  @NonFinal
  protected String SIGN_KEY;

  // Xac thuc token
  public StudentDto introspect(IntrospectForm request) throws JOSEException, ParseException {
    String token = request.getToken();

    JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());
    SignedJWT signedJWT = SignedJWT.parse(token);

    Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
    boolean verified = signedJWT.verify(verifier);

    if (!verified || expiryTime.before(new Date())) {
      throw new UnauthorizedException("Token khong hop le hoac da het han");
    }

    // Lay username tu token
    String username = signedJWT.getJWTClaimsSet().getSubject();

    var student = studentRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));

    return studentMapper.convertToStudentResponse(student);
  }

  // Lay token
  public AuthenticationDto authenticate(AuthenticationForm request){
    var student = studentRepository.findByUsername(request.getUsernameValue())
        .orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));

    boolean authenticated = request.getPasswordValue().equals(student.getPassword());
    if (!authenticated){
      throw new UnauthorizedException("Sinh vien chua duoc uy quyen");
    }
    var token = generateToken(request.getUsernameValue());

    return AuthenticationDto.builder()
        .token(token)
        .authenticated(true)
        .build();
  }

  private String generateToken(String username){
    JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

    JWTClaimsSet jwtClaimsSet = new Builder()
        .subject(username)
        .issuer("trunghao.com")
        .issueTime(new Date())
        .expirationTime(new Date(
            Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
        ))
        .build();

    Payload payload = new Payload(jwtClaimsSet.toJSONObject());

    JWSObject jwsObject = new JWSObject(header, payload);

    try {
      jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
      return jwsObject.serialize();

    } catch (JOSEException e) {
      log.error("Khong the tao token", e);
      throw new RuntimeException(e);
    }
  }
}
