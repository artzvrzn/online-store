package com.artzvrzn.store.auth2.service;

import com.artzvrzn.store.auth2.domain.User;
import com.artzvrzn.store.auth2.service.api.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProviderImpl implements JwtProvider {

  private final SecretKey secretAccess;
  private final SecretKey refreshSecret;

  public JwtProviderImpl(
    @Value("${jwt.secret.access}") String secretAccess,
    @Value("${jwt.secret.refresh}") String refreshSecret
  ) {
    this.secretAccess = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretAccess));
    this.refreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
  }

  @Override
  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setExpiration(expirationDate(5, ChronoUnit.MINUTES))
        .signWith(secretAccess)
        .setClaims(user.getAttributesMap())
        .compact();
  }

  @Override
  public String generateRefreshToken(User user) {
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setExpiration(expirationDate(30, ChronoUnit.DAYS))
        .signWith(refreshSecret)
        .compact();
  }

  @Override
  public boolean validateAccessToken(String accessToken) {
    return validateToken(accessToken, secretAccess);
  }

  @Override
  public boolean validateRefreshToken(String refreshToken) {
    return validateToken(refreshToken, refreshSecret);
  }

  @Override
  public Claims getRefreshTokenClaims(String refreshToken) {
    return getClaims(refreshToken, refreshSecret);
  }

  @Override
  public Claims getAccessTokenClaims(String accessToken) {
    return getClaims(accessToken, secretAccess);
  }

  private boolean validateToken(String token, Key secret) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(secret)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      log.error("invalid token");
    }
    return false;
  }

  private Claims getClaims(String token, Key secret) {
    return Jwts.parserBuilder()
        .setSigningKey(secret)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Date expirationDate(long amount, TemporalUnit unit) {
    LocalDateTime currentTime = LocalDateTime.now();
    Instant expirationInstant = currentTime.plus(amount, unit).toInstant(ZoneOffset.UTC);
    return Date.from(expirationInstant);
  }
}
