package com.ssafy.b105.auth.Jwt.domain;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.Date;

public final class Jwt {

  private final String issuer;

  private final String secret;

  private final long expirySeconds;

  private final Algorithm algorithm;

  private final JWTVerifier jwtVerifier;

  public Jwt(JwtTokenConfig tokenConfig) {
    this.issuer = tokenConfig.getIssuer();
    this.secret = tokenConfig.getSecret();
    this.expirySeconds = tokenConfig.getExpirySeconds();
    this.algorithm = Algorithm.HMAC256(secret);
    this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
      .withIssuer(issuer)
      .build();
  }

  public String newToken(Claims claims) {
    Date now = new Date();
    JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
    builder.withIssuer(issuer);
    builder.withIssuedAt(now);
    if (expirySeconds > 0) {
      Date expiredDate = new Date(now.getTime() + (expirySeconds * 1000));
      builder.withExpiresAt(expiredDate);
    }
    builder.withClaim("id", claims.id);
    builder.withClaim("nickname", claims.nickname);
    builder.withArrayClaim("roles", claims.roles);
    return builder.sign(algorithm);
  }

  public String refreshToken(String token) throws JWTVerificationException {
    Claims claims = verify(token);
    claims.eraseIat();
    claims.eraseExp();
    return newToken(claims);
  }

  public Claims verify(String token) throws JWTVerificationException {
    return new Claims(jwtVerifier.verify(token));
  }

  public String getIssuer() {
    return issuer;
  }

  public String getSecret() {
    return secret;
  }

  public long getExpirySeconds() {
    return expirySeconds;
  }

  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public JWTVerifier getJwtVerifier() {
    return jwtVerifier;
  }

  static public class Claims {
    Long id;
    String principal;
    String nickname;
    String[] roles;
    Date iat;
    Date exp;

    private Claims() {
    }

    Claims(DecodedJWT decodedJWT) {
      Claim id = decodedJWT.getClaim("id");
      if (!id.isNull())
        this.id = id.asLong();
      Claim principal = decodedJWT.getClaim("nickname");
      if (!principal.isNull())
        this.principal = principal.asString();
      Claim nickname = decodedJWT.getClaim("");
      if (!nickname.isNull())
        this.nickname = nickname.asString();
      Claim roles = decodedJWT.getClaim("roles");
      if (!roles.isNull())
        this.roles = roles.asArray(String.class);
      this.iat = decodedJWT.getIssuedAt();
      this.exp = decodedJWT.getExpiresAt();
    }

    public static Claims of(Long id, String principal, String name, String[] roles) {
      Claims claims = new Claims();
      claims.id = id;
      claims.principal = principal;
      claims.nickname = name;
      claims.roles = roles;
      return claims;
    }

    public Long getId() {
      return id;
    }

    public String getPrincipal() {
      return principal;
    }

    public String getNickname() {
      return nickname;
    }

    public String[] getRoles() {
      return roles;
    }

    public long iat() {
      return iat != null ? iat.getTime() : -1;
    }

    public long exp() {
      return exp != null ? exp.getTime() : -1;
    }

    void eraseIat() {
      iat = null;
    }

    void eraseExp() {
      exp = null;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(ToStringStyle.SHORT_PREFIX_STYLE);
    }
  }
}
