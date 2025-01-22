package com.flowmate.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

public class JwtProvider {

   static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());



//    Generate jwt Token
    public static String generateToken(Authentication auth){

        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();



    }

//     Get Email from JWT Token

    public static String getEmailFromToken(String jwt){

//        Whenever we get the jwt token then we will get it with the bearer keyword from which we will get our actual token, We need to remove the seven character, B-E-A-R-E-R-_
//        So, To remove this we need to use substring

            jwt = jwt.substring(7);


        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));

    }



}
