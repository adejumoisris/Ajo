package com.AjoPay.AjoPay.utilis;

import com.AjoPay.AjoPay.constants.SecurityConstant;
import com.AjoPay.AjoPay.dto.response.TokenResponse;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class AppUtilis {
    // generate token
    public static TokenResponse generateToken(Authentication authentication){
        User user= (User) authentication.getPrincipal();

        String userName = user.getUsername(); // geting UserName
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.SECRET.getBytes());
         String access_token = JWT.create()// generating an access token
                 .withSubject(userName)
                 .withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
                 .withIssuedAt(new Date(System.currentTimeMillis()))
                 .withClaim("roles", authentication.getAuthorities()
                         .stream()
                         .map(GrantedAuthority :: getAuthority)
                         .collect(Collectors.toList())

                 ).sign(algorithm);
         return new TokenResponse(access_token);
    }

    // verify token

    public static UsernamePasswordAuthenticationToken verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.SECRET.getBytes());
            JWTVerifier Verify = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = Verify.verify(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            stream(roles).forEach(role ->{
                authorities.add(new SimpleGrantedAuthority(role));
            });
            return new UsernamePasswordAuthenticationToken(username,null, authorities);

        }catch (Exception exception){
            throw new CustomException("Unthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
