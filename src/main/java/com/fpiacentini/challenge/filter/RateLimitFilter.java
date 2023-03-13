package com.fpiacentini.challenge.filter;

import com.fpiacentini.challenge.ratelimit.BucketManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final BucketManager bucketManager;

    public RateLimitFilter(@Autowired BucketManager bucketManager){
        this.bucketManager = bucketManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!bucketManager.tryConsume()) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("text/plain");
            response.getWriter().append("Too many requests");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
