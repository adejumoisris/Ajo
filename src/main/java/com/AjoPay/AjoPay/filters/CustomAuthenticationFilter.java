package com.AjoPay.AjoPay.filters;

import com.AjoPay.AjoPay.constant.SecurityConstants;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.AjoPay.AjoPay.utilis.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException , CustomException,ServletException  {
        if (request.getServletPath().equals("/auth/login")){
            filterChain.doFilter(request,response);
        }else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
                try {
                    String token = authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
                    UsernamePasswordAuthenticationToken authenticationToken = SecurityUtils.verifyToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    exception.printStackTrace();
                    log.error("Error occured {}",exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    throw new CustomException(exception.getMessage());
                }
            }else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
