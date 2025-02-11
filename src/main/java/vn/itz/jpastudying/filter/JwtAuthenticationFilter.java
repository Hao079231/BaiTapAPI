package vn.itz.jpastudying.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
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

      if (jwtService.isValid(token, studentDetails)) {
        List<?> rawAuthorities = jwtService.extractAuthorities(token);
        List<SimpleGrantedAuthority> authorityList = rawAuthorities.stream()
            .map(obj -> {
              if (obj instanceof String) {
                return new SimpleGrantedAuthority((String) obj);
              } else if (obj instanceof java.util.Map) {
                Object authority = ((java.util.Map<?, ?>) obj).get("authority");
                return new SimpleGrantedAuthority(authority != null ? authority.toString() : "");
              }
              return null;
            })
            .filter(auth -> auth != null && !auth.getAuthority().isEmpty())
            .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            studentDetails, null, authorityList
        );

        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
