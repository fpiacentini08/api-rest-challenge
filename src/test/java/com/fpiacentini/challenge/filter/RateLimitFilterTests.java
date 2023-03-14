package com.fpiacentini.challenge.filter;

import com.fpiacentini.challenge.ratelimit.BucketManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RateLimitFilterTests {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final FilterChain filterChain = mock(FilterChain.class);
    private final BucketManager bucketManager = mock(BucketManager.class);
    private final PrintWriter printWriter = mock(PrintWriter.class);

    @Test
    void givenBucketWithTokens_whenExecuteFilter_shouldNotModifyTheResponse() throws ServletException, IOException {
        var rateLimitFilter = new RateLimitFilter(bucketManager);
        when(bucketManager.tryConsume()).thenReturn(true);

        rateLimitFilter.doFilterInternal(request, response, filterChain);

        verify(response, times(0)).setStatus(429);
        verify(response, times(0)).setContentType("text/plain");
        verify(response, times(0)).getWriter();
        verify(filterChain, times(1)).doFilter(request, response);

    }

    @Test
    void givenBucketWithNoTokens_whenExecuteFilter_shouldModifyTheResponse() throws ServletException, IOException {
        var rateLimitFilter = new RateLimitFilter(bucketManager);
        when(bucketManager.tryConsume()).thenReturn(false);
        when(response.getWriter()).thenReturn(printWriter);
        when(printWriter.append(any())).thenReturn(printWriter);

        rateLimitFilter.doFilterInternal(request, response, filterChain);

        verify(response, times(1)).setStatus(429);
        verify(response, times(1)).setContentType("text/plain");
        verify(response, times(1)).getWriter();
        verify(filterChain, times(0)).doFilter(request, response);

    }

}
