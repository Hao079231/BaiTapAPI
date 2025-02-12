package vn.itz.jpastudying.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.service.JwtService;
import vn.itz.jpastudying.service.StudentDetailServiceImp;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private StudentDetailServiceImp studentDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);
    if (token.isEmpty()) {
      filterChain.doFilter(request, response);
      return;
    }

    String username = jwtService.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails studentDetails = studentDetailsService.loadUserByUsername(username);

      if (!jwtService.isValid(token, studentDetails)) {
        throw new ResourceNotFound("Token khong hop le hoac da het han", HttpStatus.NOT_FOUND);
      }

      // Lay danh sach cac quyen StudentDetailServiceImp
      List<String> authorities = studentDetailsService.getUserAuthorities(studentDetails);

      // Chuyen danh sach cac quyen thanh danh sach GrantedAuthority
      List<GrantedAuthority> grantedAuthorities = authorities.stream()
          .map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());

      // Tao Authentication moi voi danh sach cac quyen
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(studentDetails, null,
              grantedAuthorities);

      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // Luu Authentication vao SecurityContext
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
  }

}
