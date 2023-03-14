package com.guavapay.testtask.security.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long expirationTimeout;

    @Autowired
    private UserDetailsService userDetailsService;
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
    public String createToken(String username){
        List<String> roles = this.userDetailsService.loadUserByUsername(username).getAuthorities().stream().map(a->a.getAuthority()).toList();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles",roles);
        Date now = new Date();
        Date inExpirationTimeout = new Date(now.getTime() + expirationTimeout);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(inExpirationTimeout)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest req){
        String token = req.getHeader("JwtAuth");
        if(token!= null){
            return token;
        }
        return null;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if(claimsJws.getBody().getExpiration().before(new Date())){
                return false;
            }

            return true;
        }catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthenticationException("Jwt token invalid or expired");
        }
    }
}
