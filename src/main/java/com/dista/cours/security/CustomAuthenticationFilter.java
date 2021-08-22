package com.dista.cours.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authManager;

    public CustomAuthenticationFilter(AuthenticationManager auth) {
        this.authManager = auth;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain) throws ServletException, IOException {

        String username = request.getHeader("foo_username");
        String password = request.getHeader("foo_password");

        if (username == null && password == null) {
            // not our responsibility. delegate down the chain. maybe a different filter will understand this request.
            chain.doFilter(request, response);
            return;
        } else if (username == null || password == null) {
            // user is clearly trying to authenticate against the CustomAuthenticationFilter, but has done something wrong.
            response.setStatus(401);
            return;
        }

        // construct one of Spring's auth tokens
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        // delegate checking the validity of that token to our authManager
        Authentication userPassAuth = this.authManager.authenticate(authentication);
        // store completed authentication in security context
        SecurityContextHolder.getContext().setAuthentication(userPassAuth);
        // continue down the chain.
        chain.doFilter(request, response);
    }
}