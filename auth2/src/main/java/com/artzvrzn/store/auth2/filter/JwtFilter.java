package com.artzvrzn.store.auth2.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.artzvrzn.store.auth2.domain.JwtAuthentication;
import com.artzvrzn.store.auth2.service.api.JwtProvider;
import com.artzvrzn.store.auth2.util.JwtUtils;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
  private final JwtProvider jwtProvider;


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
      throws IOException, ServletException {
    String token = getTokenFromRequest((HttpServletRequest) request);
    if (token != null && jwtProvider.validateAccessToken(token)) {
      Claims claims = jwtProvider.getAccessTokenClaims(token);
      JwtAuthentication authentication = JwtUtils.generate(claims);
      authentication.setAuthenticated(true);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    fc.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    final String bearer = request.getHeader(AUTHORIZATION);
    if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }
}
