package br.com.jader.desafio.ekam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtRequestFilter extends GenericFilterBean {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            String username = jwtTokenUtil.extractUsername(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Authentication authentication = jwtTokenUtil.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
