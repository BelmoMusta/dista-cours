package com.dista.cours.security.jwt;

import com.dista.cours.AntMatchersHolder;
import com.dista.cours.exception.AuthenticationException;
import com.dista.cours.i18n.MessagesKeys;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver handlerExceptionResolver;
	@Autowired
	private AntMatchersHolder antMatchersHolder;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (isPermittedRoute(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		boolean doFilterChain = true;
		try {
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				username = getUsername(jwtToken);
			}
		} catch (Exception e) {
			doFilterChain = false;
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
						= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		if (doFilterChain) {
			filterChain.doFilter(request, response);
		}
	}
	
	private boolean isPermittedRoute(HttpServletRequest request) {
		return antMatchersHolder.isPermittedRoute(request.getRequestURI());
	}
	
	private String getUsername(String jwtToken) {
		String username;
		try {
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException ex) {
			throw new AuthenticationException(MessagesKeys.INVALIDE_JWT_TOKEN.getValue());
		} catch (ExpiredJwtException expiredException) {
			throw new AuthenticationException(MessagesKeys.JWT_TOKEN_EXPIRED.getValue());
		}
		return username;
	}
}
