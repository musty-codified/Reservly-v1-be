package com.mustycodified.Reservlyv1be.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data
public class JwtUtils {
    public static boolean hasTokenExpired(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.getTokenSecret())
                .parseClaimsJws(token).getBody();

        Date tokenExpirationDate = claims.getExpiration();
        Date todayDate = new Date();

        return tokenExpirationDate.before(todayDate);
    }

    public String generateEmailVerificationToken(String email){

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + 7200000);
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
              return token;
    }
                  public String generatePasswordResetToken(String email){
                      Date currentDate = new Date();
                      Date expirationDate = new Date(currentDate.getTime() + 3600000);
                      String token = Jwts.builder()
                              .setSubject(email)
                              .setExpiration(expirationDate)
                              .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                              .compact();
                      return token;

                  }
    }

